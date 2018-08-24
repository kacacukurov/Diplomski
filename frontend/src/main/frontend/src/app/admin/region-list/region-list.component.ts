import { Component, OnInit } from '@angular/core';
import {regionDTO} from "../../shared/models/regionDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {newRegionDTO} from "../../shared/models/newRegionDTO";
import {Router} from "@angular/router";
import {RegionService} from "../../core/services/region.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {RegionModalComponent} from "../region-modal/region-modal.component";

@Component({
  selector: 'app-region-list',
  templateUrl: './region-list.component.html',
  styleUrls: ['./region-list.component.css']
})
export class RegionListComponent implements OnInit {

  listRegions: Array<regionDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  newRegion: newRegionDTO;
  changedRegion: regionDTO;

  constructor(private router: Router, private regionService: RegionService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listRegions = [];
    this.loadRegions();
  }

  ngOnInit() {
  }

  loadRegions(){
    this.regionService.allRegions().subscribe(data =>{
      this.listRegions = data;
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

  deleteRegion(region: regionDTO){
    this.regionService.deleteRegion(region.id).subscribe(data =>{
      this.regionService.allRegions().subscribe(data =>{
        this.listRegions = data;
      });
      this.toasterService.pop('success', 'Success', 'Region deleted!');
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

  addRegion(){
    this.modalRef = this.modalService.show(
      RegionModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.change = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.newRegionReady.subscribe( data => {
      this.newRegion = data;
      this.regionService.addNewRegion(this.newRegion).subscribe(data => {
        this.regionService.allRegions().subscribe(data => {
          this.listRegions = data;
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

  changeRegion(regionDto: regionDTO){
    this.modalRef = this.modalService.show(
      RegionModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.change = true;
    this.changedRegion = new regionDTO;
    this.changedRegion.regionName = regionDto.regionName;
    this.changedRegion.id = regionDto.id;
    this.changedRegion.superRegion = regionDto.superRegion;
    this.changedRegion.locatedIn = null;
    this.modalRef.content.changeRegionDTO = this.changedRegion;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.changeRegionReady.subscribe( data => {
      this.changedRegion = data;
      this.regionService.changeRegion(this.changedRegion).subscribe(data => {
        this.regionService.allRegions().subscribe(data => {
          this.listRegions = data;
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
}
