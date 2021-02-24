import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {AllBooksComponent} from './admin/all-books/all-books.component';
import {AdminBookComponent} from './admin/admin-book/admin-book.component';
import {LoginUserComponent} from './user/login-user/login-user.component';
import {RegistrationComponent} from "./user/registration/registration.component";
import {MainComponent} from "./user/main/main.component";
import {UserProfileComponent} from "./user/user-profile/user-profile.component";


const appRoutes = [
  {path: '', component: MainComponent},
  {path: 'editbook', component: AllBooksComponent},
  {path: 'login', component: LoginUserComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'profile', component: UserProfileComponent},
  {path: 'addbook/:id', component: AdminBookComponent},
  {path: 'addbook', component: AdminBookComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
