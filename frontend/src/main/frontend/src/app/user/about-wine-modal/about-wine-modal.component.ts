import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {wineDTO} from "../../shared/models/wineDTO";
import {ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-about-wine-modal',
  templateUrl: './about-wine-modal.component.html',
  styleUrls: ['./about-wine-modal.component.css']
})
export class AboutWineModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() wine: wineDTO;
  closeReady: EventEmitter<wineDTO>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService) {
    this.closeReady = new EventEmitter();
  }

  ngOnInit() {
  }

  closeModal(){
    this.closeReady.emit(this.wine);
    this.modalRef.hide();
  }

}
