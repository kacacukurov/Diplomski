import {Component, OnInit} from '@angular/core';
import {wineDTO} from "../../shared/models/wineDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {WineService} from "../../core/services/wine.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {AboutWineModalComponent} from "../about-wine-modal/about-wine-modal.component";
import {filterDTO} from "../../shared/models/filterDTO";
import {DroolsService} from "../../core/services/drools.service";

@Component({
  selector: 'app-filter-wines',
  templateUrl: './filter-wines.component.html',
  styleUrls: ['./filter-wines.component.css']
})
export class FilterWinesComponent implements OnInit {

  listWines: Array<wineDTO>;
  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  colors: Array<string>;
  bodies: Array<string>;
  flavors: Array<string>;
  sugars: Array<string>;

  wineColor: string;
  wineBody: string;
  wineFlavor: string;
  wineSugar: string;

  filterDTO: filterDTO;

  constructor(private router: Router, private wineService: WineService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService, private droolsService: DroolsService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listWines = [];
    this.loadWines();
    this.colors = ["RED", "ROSE", "WHITE"];
    this.bodies = ["FULL", "MEDIUM", "LIGHT"];
    this.flavors = ["STRONG", "MODERATE", "DELICATE"];
    this.sugars = ["SWEET", "OFFDRY", "DRY"];
    this.filterDTO = new filterDTO;
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

  moreAboutWine(wine: wineDTO){
    this.modalRef = this.modalService.show(
      AboutWineModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.wine = wine;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.closeReady.subscribe( data => {
    });
  }

  reset(){
    this.wineBody = null;
    this.wineSugar = null;
    this.wineFlavor = null;
    this.wineColor = null;
    this.filterDTO = new filterDTO;
    this.loadWines();
  }

  filter(){
    this.filterDTO.wineBody = this.wineBody;
    this.filterDTO.wineSugar = this.wineSugar;
    this.filterDTO.wineFlavor = this.wineFlavor;
    this.filterDTO.wineColor = this.wineColor;
    console.log(this.filterDTO);

    this.droolsService.filterWines(this.filterDTO).subscribe(data =>{
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
}
