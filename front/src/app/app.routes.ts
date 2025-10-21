import { Routes } from '@angular/router';
import { LoginLayout } from './components/login-layout/login-layout';

export const routes: Routes = [
{
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
},

    {
    path: 'login',
    component: LoginLayout
}


];
