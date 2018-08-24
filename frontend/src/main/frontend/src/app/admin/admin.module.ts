import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import {SharedModule} from "../shared/shared.module";
import {AdminRouterModule} from "./admin-router.module";
import {ToasterModule} from "angular5-toaster/dist";
import { UserModalComponent } from './user-modal/user-modal.component';
import { GrapeListComponent } from './grape-list/grape-list.component';
import { RegionListComponent } from './region-list/region-list.component';
import { WineListComponent } from './wine-list/wine-list.component';
import { WineryListComponent } from './winery-list/winery-list.component';
import { GrapeModalComponent } from './grape-modal/grape-modal.component';
import { WineModalComponent } from './wine-modal/wine-modal.component';
import { WineryModalComponent } from './winery-modal/winery-modal.component';
import { RegionModalComponent } from './region-modal/region-modal.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AdminRouterModule,
    ToasterModule
  ],
  declarations: [
    UserListComponent,
    UserModalComponent,
    GrapeListComponent,
    RegionListComponent,
    WineListComponent,
    WineryListComponent,
    GrapeModalComponent,
    WineModalComponent,
    WineryModalComponent,
    RegionModalComponent],
  entryComponents: [
    UserModalComponent,
    GrapeModalComponent,
    WineModalComponent,
    WineryModalComponent,
    RegionModalComponent
  ]
})
export class AdminModule { }
