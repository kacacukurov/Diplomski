import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {GrapeService} from "../../core/services/grape.service";

@Component({
  selector: 'app-grape-modal',
  templateUrl: './grape-modal.component.html',
  styleUrls: ['./grape-modal.component.css']
})
export class GrapeModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() grapeName: string;

  userReady: EventEmitter<string>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private grapeService: GrapeService) {
    this.grapeName = '';
    this.userReady = new EventEmitter();
  }

  ngOnInit() {
  }

  sendData(){
    this.userReady.emit(this.grapeName);
    this.modalRef.hide();
  }

}
