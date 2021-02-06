import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { AdminBookComponent } from './admin-book/admin-book.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginUserComponent } from './login-user/login-user.component';
import {Interceptor} from "./controllers/Interceptor";
import { ModalWindowComponent } from './modal-window/modal-window.component';
import { HeaderComponent } from './header/header.component';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AdminBookComponent,
    LoginUserComponent,
    ModalWindowComponent,
    HeaderComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
