import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { AccountsComponent } from './features/accounts/accounts.component';

const routes: Routes = [
  {
    path:'',
    redirectTo:'auth/login',
    pathMatch:'full'
  },
  {
    path:'auth/login',
    component:LoginComponent
  },
  {
    path:'auth/register',
    component:RegisterComponent
  },
{
  path:'accounts',
  component:AccountsComponent
}

]



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
