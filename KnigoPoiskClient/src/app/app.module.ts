import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { AdminBookComponent } from './admin-book/admin-book.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginUserComponent } from './login-user/login-user.component';
import {JwtInterceptor} from "./controllers/JwtInterceptor";

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AdminBookComponent,
    LoginUserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
