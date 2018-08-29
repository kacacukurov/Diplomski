import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {FindGrapesComponent} from "./find-grapes/find-grapes.component";
import {FindWinesComponent} from "./find-wines/find-wines.component";
import {FindWinesAndGrapesComponent} from "./find-wines-and-grapes/find-wines-and-grapes.component";

const routes: Routes = [
  { path: '', component: FindWinesComponent },
  { path: 'grapes', component: FindGrapesComponent },
  { path: 'wineGrapes', component: FindWinesAndGrapesComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UserRouterModule { }
