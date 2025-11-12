import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountsRoutingModule } from './accounts-routing.module';
import { AccountsComponent } from './accounts.component';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountCreateComponent } from './account-create/account-create.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AccountsComponent,
    AccountListComponent,
  ],
  imports: [
    CommonModule,
    AccountsRoutingModule,
    AccountCreateComponent,
    RouterModule
  ]
})
export class AccountsModule { }
