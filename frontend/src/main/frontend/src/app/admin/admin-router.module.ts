import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
// component
import {UserListComponent} from "./user-list/user-list.component";
import {GrapeListComponent} from "./grape-list/grape-list.component";
import {RegionListComponent} from "./region-list/region-list.component";
import {WineListComponent} from "./wine-list/wine-list.component";
import {WineryListComponent} from "./winery-list/winery-list.component";

const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'grape', component: GrapeListComponent },
  { path: 'region', component: RegionListComponent },
  { path: 'wine', component: WineListComponent },
  { path: 'winery', component: WineryListComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AdminRouterModule { }
