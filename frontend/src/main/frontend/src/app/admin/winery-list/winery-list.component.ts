import { Component, OnInit } from '@angular/core';
import {wineryDTO} from "../../shared/models/wineryDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {WineryService} from "../../core/services/winery.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {WineryModalComponent} from "../winery-modal/winery-modal.component";

@Component({
  selector: 'app-winery-list',
  templateUrl: './winery-list.component.html',
  styleUrls: ['./winery-list.component.css']
})
export class WineryListComponent implements OnInit {

  listWineries: Array<wineryDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  newWineryName: string;

  constructor(private router: Router, private wineryService: WineryService, private jwtService: JwtService,
              private toasterService: ToasterService, private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listWineries = [];
    this.loadWineries();
  }

  ngOnInit() {
  }

  loadWineries(){
    this.wineryService.allWineries().subscribe(data =>{
      this.listWineries = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Wineries not found!');
      else if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'You do not have permission for this action!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Bad request!');
      else {
        this.toasterService.pop('error', 'Error', 'Error, look at console!');
        throw error;
      }
    });
  }

  deleteWinery(winery: wineryDTO){
    this.wineryService.deleteWinery(winery.id).subscribe(data =>{
      this.wineryService.allWineries().subscribe(data =>{
        this.listWineries = data;
      });
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'You do not have permission for this action!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'bad request!');
      else {
        this.toasterService.pop('error', 'Error', 'Error, look at console!');
        throw error;
      }
    });
    this.toasterService.pop('success', 'Success', 'Winery deleted!');
  }

  addWinery(){
    this.modalRef = this.modalService.show(
      WineryModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.userReady.subscribe( data => {
      this.newWineryName = data;
      this.wineryService.addNewWinery(this.newWineryName).subscribe(data =>{
        this.wineryService.allWineries().subscribe(data =>{
          this.listWineries = data;
        });
      },(error: AppError) => {
        if(error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'You do not have permission for this action!');
        else if(error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error', 'bad request!');
        else {
          this.toasterService.pop('error', 'Error', 'Error, look at console!');
          throw error;
        }
      });
    })
  }

}
