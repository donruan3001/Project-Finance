import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BanksRoutingModule } from './banks-routing.module';
import { BanksComponent } from './banks.component';
import { BankListComponent } from './bank-list/bank-list.component';
import { BankCreateComponent } from './bank-create/bank-create.component';


@NgModule({
  declarations: [
    BanksComponent,
    BankListComponent,
    BankCreateComponent
  ],
  imports: [
    CommonModule,
    BanksRoutingModule
  ]
})
export class BanksModule { }
