import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AllBooksComponent } from './admin/all-books/all-books.component';
import { AdminBookComponent } from './admin/admin-book/admin-book.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginUserComponent } from './user/login-user/login-user.component';
import {Interceptor} from "./utils/controllers/Interceptor";
import { ModalWindowComponent } from './utils/modal-window/modal-window.component';
import { HeaderComponent } from './header/header.component';
import { RegistrationComponent } from './user/registration/registration.component';
import { MainComponent } from './user/main/main.component';
import {JwtService} from "./utils/controllers/JwtService";
import { UserProfileComponent } from './user/user-profile/user-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    AllBooksComponent,
    AdminBookComponent,
    LoginUserComponent,
    ModalWindowComponent,
    HeaderComponent,
    RegistrationComponent,
    MainComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true},
    JwtService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
