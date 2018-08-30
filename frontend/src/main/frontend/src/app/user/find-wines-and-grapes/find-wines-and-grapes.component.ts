import {Component, OnInit} from '@angular/core';
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {grapeDTO} from "../../shared/models/grapeDTO";
import {grapeListDTO} from "../../shared/models/grapeListDTO";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {GrapeService} from "../../core/services/grape.service";
import {JwtService} from "../../core/services/jwt.service";
import {DroolsService} from "../../core/services/drools.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {missingGrapesDTO} from "../../shared/models/missingGrapesDTO";
import {WineGrapesListModalComponent} from "../wine-grapes-list-modal/wine-grapes-list-modal.component";

@Component({
  selector: 'app-find-wines-and-grapes',
  templateUrl: './find-wines-and-grapes.component.html',
  styleUrls: ['./find-wines-and-grapes.component.css']
})
export class FindWinesAndGrapesComponent implements OnInit {

  toasterConfig : ToasterConfig;
  listGrapes: Array<grapeDTO>;
  listChonesGrapes: Array<grapeDTO>;
  existingGrapes: Array<grapeDTO>;
  posibleWinesAndGrapes: Array<missingGrapesDTO>;
  grapeListDTO: grapeListDTO;
  modalRef: BsModalRef;

  constructor(private router: Router, private grapeService: GrapeService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService, private droolsService: DroolsService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listGrapes = [];
    this.listChonesGrapes = [];
    this.existingGrapes = [];
    this.posibleWinesAndGrapes = [];
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
    this.posibleWinesAndGrapes = [];
    this.listGrapes.sort(function (obj1, obj2) {
      return obj1.id - obj2.id
    })
  }

  getWine(){
    this.grapeListDTO.grapeDTOs = this.listChonesGrapes;
    this.droolsService.getPotentialWines(this.grapeListDTO).subscribe(data =>{
      this.posibleWinesAndGrapes = data;
      if(this.posibleWinesAndGrapes.length == 0)
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

  moreAboutWine(possible: missingGrapesDTO){

    this.modalRef = this.modalService.show(
      WineGrapesListModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.existingGrapes = [];
    console.log(possible.wineDTO.grapeDTOS);
    console.log(possible.grapeDTOS);
    for(let grape of possible.wineDTO.grapeDTOS){
      let index = -1;
      for(let g of possible.grapeDTOS){
        if(g.id == grape.id)
          index = 1;
      }
      if(index == -1){
        this.existingGrapes.push(grape);
      }
    }
    console.log(this.existingGrapes);
    this.modalRef.content.wine = possible;
    this.modalRef.content.existingGrapes = this.existingGrapes;
    this.modalRef.content.missingGrapes = possible.grapeDTOS;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.closeReady.subscribe( data => {
    });
  }

}
