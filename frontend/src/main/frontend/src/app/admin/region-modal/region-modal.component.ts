import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {newRegionDTO} from "../../shared/models/newRegionDTO";
import {regionDTO} from "../../shared/models/regionDTO";
import {ToasterService} from "angular5-toaster/dist";
import {RegionService} from "../../core/services/region.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";

@Component({
  selector: 'app-region-modal',
  templateUrl: './region-modal.component.html',
  styleUrls: ['./region-modal.component.css']
})
export class RegionModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() newRegionDTO: newRegionDTO;
  @Input() changeRegionDTO: regionDTO;
  @Input() change: boolean;

  newRegionReady: EventEmitter<newRegionDTO>;
  changeRegionReady: EventEmitter<regionDTO>;
  listRegions: Array<regionDTO>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private regionService: RegionService) {
    this.newRegionDTO = new newRegionDTO;
    this.changeRegionDTO = new regionDTO;
    this.change = false;
    this.newRegionReady = new EventEmitter();
    this.changeRegionReady = new EventEmitter();

  }

  ngOnInit() {
    this.loadRegions();
  }

  loadRegions(){
    this.regionService.allRegions().subscribe(data =>{
      this.listRegions = data;
      let sameRegion = this.listRegions.find(region => region.regionName == this.changeRegionDTO.regionName);
      let index = this.listRegions.indexOf(sameRegion, 0);
      if (index > -1) {
        this.listRegions.splice(index, 1);
      }
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

  sendData(){
    if(this.change == true)
      this.changeRegion();
    else
      this.saveRegion();
  }

  saveRegion(){
    this.newRegionReady.emit(this.newRegionDTO);
    this.modalRef.hide();
  }

  changeRegion(){
    this.changeRegionReady.emit(this.changeRegionDTO);
    this.modalRef.hide();
  }
}
