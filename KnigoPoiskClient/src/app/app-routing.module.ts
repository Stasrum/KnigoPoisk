import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {MainComponent} from './main/main.component';
import {AdminBookComponent} from './admin-book/admin-book.component';
import {LoginUserComponent} from './login-user/login-user.component';


const appRoutes = [
  {path: '', component: MainComponent},
  {path: 'login', component: LoginUserComponent},
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
