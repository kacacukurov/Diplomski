import {Component, OnInit} from '@angular/core';
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {grapeDTO} from "../../shared/models/grapeDTO";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {GrapeService} from "../../core/services/grape.service";
import {JwtService} from "../../core/services/jwt.service";
import {DroolsService} from "../../core/services/drools.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {wineDTO} from "../../shared/models/wineDTO";
import {WineService} from "../../core/services/wine.service";
import {missingGrapesDTO} from "../../shared/models/missingGrapesDTO";

@Component({
  selector: 'app-find-grapes',
  templateUrl: './find-grapes.component.html',
  styleUrls: ['./find-grapes.component.css']
})
export class FindGrapesComponent implements OnInit {

  toasterConfig : ToasterConfig;
  listGrapes: Array<grapeDTO>;
  chosenWineId: number;
  listWines: Array<wineDTO>;
  listChosenGrapes: Array<grapeDTO>;
  missingGrapesDTO: missingGrapesDTO;
  listFoundGrapes: Array<grapeDTO>;

  modalRef: BsModalRef;

  constructor(private router: Router, private grapeService: GrapeService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService,
              private droolsService: DroolsService, private wineService: WineService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listGrapes = [];
    this.listWines = [];
    this.listChosenGrapes = [];
    this.listFoundGrapes = [];
    this.missingGrapesDTO = new missingGrapesDTO();
    this.loadGrapes();
    this.loadWines();
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

  addGrape(grape: grapeDTO){
    let index = this.listGrapes.indexOf(grape, 0);
    if (index > -1) {
      this.listGrapes.splice(index, 1);
    }
    this.listChosenGrapes.push(grape);
    this.listChosenGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }

  removeGrape(grape: grapeDTO){
    let index = this.listChosenGrapes.indexOf(grape, 0);
    if (index > -1) {
      this.listChosenGrapes.splice(index, 1);
    }
    this.listGrapes.push(grape);
    this.listGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }

  clearAll(){
    for(let grape of this.listChosenGrapes){
      this.listGrapes.push(grape);
    }
    this.listChosenGrapes = [];
    this.listGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    });
    this.listFoundGrapes = [];
  }

  getGrapes(){

    for(let w of this.listWines){
      if(w.id == this.chosenWineId)
        this.missingGrapesDTO.wineDTO = w;
    }
    this.missingGrapesDTO.grapeDTOS = this.listChosenGrapes;

    this.droolsService.getMissingGrapesForWine(this.missingGrapesDTO).subscribe(data =>{
      this.listFoundGrapes = data;
      if(this.listFoundGrapes.length == 0)
        this.toasterService.pop('info', 'Info', 'You can\'t make chosen wine from chosen grapes!');
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

}
