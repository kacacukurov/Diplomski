import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {newWineDTO} from "../../shared/models/newWineDTO";
import {wineDTO} from "../../shared/models/wineDTO";
import {regionDTO} from "../../shared/models/regionDTO";
import {grapeDTO} from "../../shared/models/grapeDTO";
import {ToasterService} from "angular5-toaster/dist";
import {RegionService} from "../../core/services/region.service";
import {WineService} from "../../core/services/wine.service";
import {GrapeService} from "../../core/services/grape.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {wineryDTO} from "../../shared/models/wineryDTO";
import {WineryService} from "../../core/services/winery.service";

@Component({
  selector: 'app-wine-modal',
  templateUrl: './wine-modal.component.html',
  styleUrls: ['./wine-modal.component.css']
})
export class WineModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() newWineDTO: newWineDTO;
  @Input() changeWineDTO: wineDTO;
  @Input() change: boolean;

  newWineReady: EventEmitter<newWineDTO>;
  changeWineReady: EventEmitter<wineDTO>;
  listRegions: Array<regionDTO>;
  listGrapes: Array<grapeDTO>;
  firstGrape: string;
  listWines: Array<wineDTO>;
  listWineries: Array<wineryDTO>;

  wineBody: string;
  wineBodies: Array<string>;
  wineColor: string;
  wineColors: Array<string>;
  wineSugar: string;
  wineSugars: Array<string>;
  wineFlavor: string;
  wineFlavors: Array<string>;
  addedGrapes: Array<grapeDTO>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService, private wineryService: WineryService,
              private regionService: RegionService, private wineService: WineService, private grapeService: GrapeService) {
    this.newWineDTO = new newWineDTO;
    this.newWineDTO.grapeNames = [];
    this.changeWineDTO = new wineDTO;
    this.change = false;
    this.newWineReady = new EventEmitter();
    this.changeWineReady = new EventEmitter();
    this.wineBody = 'LIGHT';
    this.wineBodies = ['LIGHT', 'MEDIUM', 'FULL'];
    this.wineColor = 'WHITE';
    this.wineColors = ['WHITE', 'ROSE', 'RED'];
    this.wineSugar = 'SWEET';
    this.wineSugars = ['SWEET', 'OFFDRY', 'DRY'];
    this.wineFlavor = 'DELICATE';
    this.wineFlavors = ['DELICATE', 'MODERATE', 'STRONG'];
    this.addedGrapes = [];
  }

  ngOnInit() {
    this.loadRegions();
    this.loadGrapes();
    this.loadWines();
    this.loadWineries();
  }

  loadRegions(){
    this.regionService.allRegions().subscribe(data =>{
      this.listRegions = data;
      this.listRegions.push(new regionDTO);
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Regions not found!');
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

  loadWineries(){
    this.wineryService.allWineries().subscribe(data =>{
      this.listWineries = data;
      this.listWineries.push(new wineryDTO);
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


  loadWines(){
    this.wineService.allWines().subscribe(data =>{
      this.listWines = data;
      let sameWine = this.listWines.find(wine => wine.wineName == this.changeWineDTO.wineName);
      let index = this.listWines.indexOf(sameWine, 0);
      if (index > -1) {
        this.listWines.splice(index, 1);
      }
      this.listWines.push(new wineDTO);
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

  loadGrapes(){
    this.grapeService.allGrapes().subscribe(data =>{
      this.listGrapes = data;
      this.firstGrape = this.listGrapes[0].grapeName;
      if(this.change){
        for(let grape of this.changeWineDTO.grapeDTOS)
          this.addedGrapes.push(grape);
      }
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

  sendData(){
    if(this.change == true)
      this.changeWine();
    else
      this.saveWine();
  }

  saveWine(){
    this.newWineDTO.wineBody = this.wineBody;
    this.newWineDTO.wineColor = this.wineColor;
    this.newWineDTO.wineSugar = this.wineSugar;
    this.newWineDTO.wineFlavor = this.wineFlavor;
    for(let grape of this.addedGrapes){
      this.newWineDTO.grapeNames.push(grape.grapeName);
    }
    this.newWineReady.emit(this.newWineDTO);
    this.modalRef.hide();
  }

  changeWine(){
    this.change = false;

    let foundWinery = false;
    this.listWineries.pop();
    for(let winery of this.listWineries){
      if(winery.wineryName == this.changeWineDTO.wineryDTO.wineryName){
        this.changeWineDTO.wineryDTO = winery;
        foundWinery = true;
      }
    }
    if(!foundWinery)
      this.changeWineDTO.wineryDTO = null;

    let foundRegion = false;
    this.listRegions.pop();
    for(let region of this.listRegions){
      if(region.regionName == this.changeWineDTO.regionDTO.regionName){
        this.changeWineDTO.regionDTO = region;
        foundRegion = true;
      }
    }
    if(!foundRegion)
      this.changeWineDTO.regionDTO = null;

    let foundSubclassof = false;
    this.listWines = this.listWines.slice(-1,1);
    for(let wine of this.listWines){
      if(wine.wineName == this.changeWineDTO.subclassOfWine){
        foundSubclassof = true;
      }
    }
    if(!foundSubclassof)
      this.changeWineDTO.subclassOfWine = null;

    this.changeWineDTO.grapeDTOS = [];
    for(let grape of this.addedGrapes){
      this.changeWineDTO.grapeDTOS.push(grape);
    }
    this.changeWineReady.emit(this.changeWineDTO);
    this.modalRef.hide();

  }

  addGrape(){
    for(let g of this.listGrapes){
      if(g.grapeName == this.firstGrape)
        this.addedGrapes.push(g);
    }
  }

  removeGrape(grape){
    let pos = this.addedGrapes.indexOf(grape);
    this.addedGrapes.splice(pos, 1);
  }

}
