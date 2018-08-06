import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
// component
import {UserListComponent} from "./user-list/user-list.component";

const routes: Routes = [
  { path: '', component: UserListComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AdminRouterModule { }
