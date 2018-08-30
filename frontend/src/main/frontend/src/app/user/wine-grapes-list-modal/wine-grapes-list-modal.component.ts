import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {wineDTO} from "../../shared/models/wineDTO";
import {ToasterService} from "angular5-toaster/dist";
import {missingGrapesDTO} from "../../shared/models/missingGrapesDTO";
import {grapeListDTO} from "../../shared/models/grapeListDTO";
import {grapeDTO} from "../../shared/models/grapeDTO";

@Component({
  selector: 'app-wine-grapes-list-modal',
  templateUrl: './wine-grapes-list-modal.component.html',
  styleUrls: ['./wine-grapes-list-modal.component.css']
})
export class WineGrapesListModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() wine: missingGrapesDTO;
  @Input() existingGrapes: Array<grapeDTO>;
  @Input() missingGrapes: Array<grapeDTO>;

  closeReady: EventEmitter<missingGrapesDTO>;


  constructor(private modalService : BsModalService,  private toasterService: ToasterService) {
    this.closeReady = new EventEmitter();
  }

  ngOnInit() {
  }

  closeModal(){
    this.closeReady.emit(this.wine);
    console.log(this.missingGrapes);
    this.modalRef.hide();
  }

}
