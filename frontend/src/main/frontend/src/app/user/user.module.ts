import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FindWinesComponent } from './find-wines/find-wines.component';
import { FindWinesAndGrapesComponent } from './find-wines-and-grapes/find-wines-and-grapes.component';
import { FindGrapesComponent } from './find-grapes/find-grapes.component';
import {UserRouterModule} from "./user-router.module";
import {ToasterModule} from "angular5-toaster/dist";
import {SharedModule} from "../shared/shared.module";
import { AboutWineModalComponent } from './about-wine-modal/about-wine-modal.component';
import { WineGrapesListModalComponent } from './wine-grapes-list-modal/wine-grapes-list-modal.component';
import { FilterWinesComponent } from './filter-wines/filter-wines.component';
import { SimilarWinesModalComponent } from './similar-wines-modal/similar-wines-modal.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    UserRouterModule,
    ToasterModule
  ],
  declarations: [
    FindWinesComponent,
    FindWinesAndGrapesComponent,
    FindGrapesComponent,
    AboutWineModalComponent,
    WineGrapesListModalComponent,
    FilterWinesComponent,
    SimilarWinesModalComponent],
  entryComponents: [
    AboutWineModalComponent,
    WineGrapesListModalComponent,
    SimilarWinesModalComponent
  ]
})
export class UserModule { }
