import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import {SharedModule} from "../shared/shared.module";
import {AdminRouterModule} from "./admin-router.module";
import {ToasterModule} from "angular5-toaster/dist";
import { UserModalComponent } from './user-modal/user-modal.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AdminRouterModule,
    ToasterModule
  ],
  declarations: [
    UserListComponent,
    UserModalComponent],
  entryComponents: [
    UserModalComponent
  ]
})
export class AdminModule { }
