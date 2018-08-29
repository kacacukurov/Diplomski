import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {GrapeService} from "../../core/services/grape.service";
import {JwtService} from "../../core/services/jwt.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalService} from "ngx-bootstrap";
import {DroolsService} from "../../core/services/drools.service";
import {grapeDTO} from "../../shared/models/grapeDTO";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {wineDTO} from "../../shared/models/wineDTO";
import {grapeListDTO} from "../../shared/models/grapeListDTO";

@Component({
  selector: 'app-find-wines',
  templateUrl: './find-wines.component.html',
  styleUrls: ['./find-wines.component.css']
})
export class FindWinesComponent implements OnInit {

  toasterConfig : ToasterConfig;
  listGrapes: Array<grapeDTO>;
  listChonesGrapes: Array<grapeDTO>;
  posibleWines: Array<wineDTO>;
  grapeListDTO: grapeListDTO;

  constructor(private router: Router, private grapeService: GrapeService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService, private droolsService: DroolsService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listGrapes = [];
    this.listChonesGrapes = [];
    this.posibleWines = [];
    this.loadGrapes();
    this.grapeListDTO = new grapeListDTO();
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

  getWine(){
    this.grapeListDTO.grapeDTOs = this.listChonesGrapes;
    this.droolsService.getPossibleWinesFromGrapes(this.grapeListDTO).subscribe(data =>{
      this.posibleWines = data;
      if(this.posibleWines.length == 0)
        this.toasterService.pop('info', 'Info', 'There are no wines for these combinations of grapes!');
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
    this.listChonesGrapes.push(grape);
    this.listChonesGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }

  removeGrape(grape: grapeDTO){
    let index = this.listChonesGrapes.indexOf(grape, 0);
    if (index > -1) {
      this.listChonesGrapes.splice(index, 1);
    }
    this.listGrapes.push(grape);
    this.listGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }

  clearAll(){
    for(let grape of this.listChonesGrapes){
      this.listGrapes.push(grape);
    }
    this.listChonesGrapes = [];
    this.posibleWines = [];
    this.listGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }
}
