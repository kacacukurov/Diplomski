import { Component, OnInit } from '@angular/core';
import {wineDTO} from "../../shared/models/wineDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {newWineDTO} from "../../shared/models/newWineDTO";
import {Router} from "@angular/router";
import {WineService} from "../../core/services/wine.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {WineModalComponent} from "../wine-modal/wine-modal.component";
import {wineryDTO} from "../../shared/models/wineryDTO";
import {regionDTO} from "../../shared/models/regionDTO";

@Component({
  selector: 'app-wine-list',
  templateUrl: './wine-list.component.html',
  styleUrls: ['./wine-list.component.css']
})
export class WineListComponent implements OnInit {

  listWines: Array<wineDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  newWine: newWineDTO;
  changedWine: wineDTO;

  constructor(private router: Router, private wineService: WineService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listWines = [];
    this.loadWines();
  }

  ngOnInit() {
  }

  loadWines(){
    this.wineService.allWines().subscribe(data =>{
      this.listWines = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Wines not found!');
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

  addWine(){
    this.modalRef = this.modalService.show(
      WineModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.change = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.newWineReady.subscribe( data => {
      this.newWine = data;
      this.wineService.addNewWine(this.newWine).subscribe(data => {
        this.wineService.allWines().subscribe(data => {
          this.listWines = data;
        });
      }, (error: AppError) => {
        if (error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'You do not have permission for this action!');
        else if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error', 'bad request!');
        else {
          this.toasterService.pop('error', 'Error', 'Error, look at console!');
          throw error;
        }
      });
    });
  }

  changeWine(wine: wineDTO){
    this.modalRef = this.modalService.show(
      WineModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.change = true;

    this.changedWine = new wineDTO;
    this.changedWine.id = wine.id;
    this.changedWine.subclassOfWine = wine.subclassOfWine;
    this.changedWine.grapeDTOS = wine.grapeDTOS;
    if(wine.wineryDTO == null)
      this.changedWine.wineryDTO = new wineryDTO;
    else
      this.changedWine.wineryDTO = wine.wineryDTO;
    if(wine.regionDTO == null)
      this.changedWine.regionDTO = new regionDTO;
    else
      this.changedWine.regionDTO = wine.regionDTO;
    this.changedWine.wineFlavor = wine.wineFlavor;
    this.changedWine.wineSugar = wine.wineSugar;
    this.changedWine.wineColor = wine.wineColor;
    this.changedWine.wineBody = wine.wineBody;
    this.changedWine.isSuperClass = wine.isSuperClass;
    this.changedWine.wineName = wine.wineName;

    this.modalRef.content.changeWineDTO = this.changedWine;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.changeWineReady.subscribe( data => {
      this.changedWine = data;
      console.log(this.changedWine);
       this.wineService.changeWine(this.changedWine).subscribe(data => {
        this.wineService.allWines().subscribe(data => {
          this.listWines = data;
        });
        this.toasterService.pop('success', 'Success', 'Wine changed!');
      }, (error: AppError) => {
        if (error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'You do not have permission for this action!');
        else if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error', 'bad request!');
        else {
          this.toasterService.pop('error', 'Error', 'Error, look at console!');
          throw error;
        }
      });
    });
  }

  deleteWine(wine: wineDTO){
    this.wineService.deleteWine(wine.id).subscribe(data =>{
      this.wineService.allWines().subscribe(data =>{
        this.listWines = data;
      });
      this.toasterService.pop('success', 'Success', 'Wine deleted!');
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
}
