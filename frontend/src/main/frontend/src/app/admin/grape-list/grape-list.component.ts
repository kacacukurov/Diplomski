import { Component, OnInit } from '@angular/core';
import {grapeDTO} from "../../shared/models/grapeDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {GrapeService} from "../../core/services/grape.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {GrapeModalComponent} from "../grape-modal/grape-modal.component";

@Component({
  selector: 'app-grape-list',
  templateUrl: './grape-list.component.html',
  styleUrls: ['./grape-list.component.css']
})
export class GrapeListComponent implements OnInit {

  listGrapes: Array<grapeDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  newGrapeName: string;

  constructor(private router: Router, private grapeService: GrapeService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listGrapes = [];
    this.loadGrapes();
  }

  ngOnInit() {
  }

  loadGrapes(){
    this.grapeService.allGrapes().subscribe(data =>{
      this.listGrapes = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Grapes not found!');
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

  deleteGrape(grape: grapeDTO){
    this.grapeService.deleteGrape(grape.id).subscribe(data =>{
      this.grapeService.allGrapes().subscribe(data =>{
        this.listGrapes = data;
      });
      this.toasterService.pop('success', 'Success', 'Grape deleted!');
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
  }

  addGrape(){
    this.modalRef = this.modalService.show(
      GrapeModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.userReady.subscribe( data => {
      this.newGrapeName = data;
      this.grapeService.addNewGrape(this.newGrapeName).subscribe(data =>{
        this.grapeService.allGrapes().subscribe(data =>{
          this.listGrapes = data;
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
