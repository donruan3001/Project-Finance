import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'auth', loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule) }, { path: 'accounts', loadChildren: () => import('./features/accounts/accounts.module').then(m => m.AccountsModule) }, { path: 'banks', loadChildren: () => import('./features/banks/banks.module').then(m => m.BanksModule) }, { path: 'transactions', loadChildren: () => import('./features/transactions/transactions.module').then(m => m.TransactionsModule) }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
