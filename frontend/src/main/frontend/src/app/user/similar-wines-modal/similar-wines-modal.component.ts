import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {wineSimilarDTO} from "../../shared/models/wineSimilarDTO";
import {ToasterService} from "angular5-toaster/dist";
import {wineDTO} from "../../shared/models/wineDTO";

@Component({
  selector: 'app-similar-wines-modal',
  templateUrl: './similar-wines-modal.component.html',
  styleUrls: ['./similar-wines-modal.component.css']
})
export class SimilarWinesModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() similarWines: Array<wineSimilarDTO>;
  @Input() chosenWine: wineDTO;
  closeReady: EventEmitter<wineDTO>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService) {
    this.closeReady = new EventEmitter();
  }

  ngOnInit() {
  }

  closeModal(){
    this.closeReady.emit(this.chosenWine);
    this.modalRef.hide();
  }
}
