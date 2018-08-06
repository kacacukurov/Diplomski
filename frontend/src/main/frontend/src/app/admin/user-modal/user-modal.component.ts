import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {registrationDTO} from "../../shared/models/registrationDTO";
import {ToasterService} from "angular5-toaster/dist";
import {UserService} from "../../core/services/user.service";
import {Login} from "../../shared/models/login";

@Component({
  selector: 'app-user-modal',
  templateUrl: './user-modal.component.html',
  styleUrls: ['./user-modal.component.css']
})
export class UserModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() registration: registrationDTO;
  @Input() admin: boolean;
  @Input() change: boolean;

  loginDto: Login;

  userReady: EventEmitter<registrationDTO>;

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private userService: UserService) {
    this.registration = new registrationDTO;
    this.loginDto = new Login('', '');
    this.registration.loginAccount = this.loginDto;
    this.registration.firstName = '';
    this.registration.lastName = '';
    this.change = false;
    this.userReady = new EventEmitter();
  }

  ngOnInit() {
  }

  sendData(){
    this.userReady.emit(this.registration);
    this.modalRef.hide();

  }
}
