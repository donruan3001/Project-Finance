import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthModule } from './features/auth/auth.module';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
        ReactiveFormsModule,
      CommonModule,
      RouterModule,
      AuthModule
  ],
  providers: [{provide: 'HTTP_INTERCEPTOR', useClass:HttpClientModule, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
