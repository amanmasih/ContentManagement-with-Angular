import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { UserloginComponent } from './userlogin/userlogin.component';

const routes: Routes = [
  {
    path: "",
    component:NavbarComponent
  },
  {
    path: "login",
    component: UserloginComponent
  },
  {
    path: "register",
    component: RegisterUserComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
