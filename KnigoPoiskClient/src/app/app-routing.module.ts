import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {MainComponent} from './main/main.component';
import {AddBookComponent} from './add-book/add-book.component';
import {LoginUserComponent} from './login-user/login-user.component';


const appRoutes = [
  {path: '', component: MainComponent},
  {path: 'login', component: LoginUserComponent},
  {path: 'addbook/:book', component: AddBookComponent},
  {path: 'addbook', component: AddBookComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
