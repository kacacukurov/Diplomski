import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {WineryService} from "../../core/services/winery.service";

@Component({
  selector: 'app-winery-modal',
  templateUrl: './winery-modal.component.html',
  styleUrls: ['./winery-modal.component.css']
})
export class WineryModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() wineryName: string;

  userReady: EventEmitter<string>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private wineryService: WineryService) {
    this.wineryName = '';
    this.userReady = new EventEmitter();
  }

  ngOnInit() {
  }

  sendData(){
    this.userReady.emit(this.wineryName);
    this.modalRef.hide();
  }
}
