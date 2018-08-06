import {Component, OnInit} from '@angular/core';
import {accountDTO} from "../../shared/models/accountDTO";
import {UserService} from "../../core/services/user.service";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserModalComponent} from "../user-modal/user-modal.component";
import {registrationDTO} from "../../shared/models/registrationDTO";
import {Login} from '../../shared/models/login';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  listAdmins: Array<accountDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  registrationNew: registrationDTO;

  constructor(private router: Router, private userService: UserService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.listAdmins = [];
    this.loadAdmins();
  }

  ngOnInit() {
  }

  loadAdmins(){
    this.userService.allAdmins().subscribe(data =>{
      this.listAdmins = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Admins not found!');
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


  changeAccount(account: accountDTO, admin: boolean){
    this.modalRef = this.modalService.show(
      UserModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.registrationNew = new registrationDTO;
    this.registrationNew.loginAccount = new Login(account.username, '');
    this.registrationNew.firstName = account.firstName;
    this.registrationNew.lastName = account.lastName;

    this.modalRef.content.change = true;
    this.modalRef.content.registration   = this.registrationNew;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.userReady.subscribe( data => {
      this.registrationNew = data;
      this.userService.changeAccount(this.registrationNew).subscribe(data =>{
        if(admin){
          this.userService.allAdmins().subscribe(data =>{
            this.listAdmins = data;
          });
        }
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
    })
  }

  deleteAccount(account: accountDTO, admin: boolean){
    this.userService.deleteAccount(account.username).subscribe(data =>{
      if(admin){
        this.userService.allAdmins().subscribe(data =>{
          this.listAdmins = data;
        });
      }
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

  addAdmin(){
    this.modalRef = this.modalService.show(
      UserModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.userReady.subscribe( data => {
      this.registrationNew = data;
      this.userService.registerAdmin(this.registrationNew).subscribe(data =>{
        this.userService.allAdmins().subscribe(data =>{
          this.listAdmins = data;
        });
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
    })
  }

}
