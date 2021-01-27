import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {MainComponent} from './main/main.component';
import {AddNewBookComponent} from './add-new-book/add-new-book.component';


const appRoutes = [
  {path: '', component: AddNewBookComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
