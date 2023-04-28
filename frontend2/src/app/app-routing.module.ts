import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUserComponent } from './create-user/create-user.component';
import { CreatePdfComponent } from './create-pdf/create-pdf.component';

const routes: Routes = [
  {
    path: 'create-user',
    component: CreateUserComponent
  },
  {
    path: 'create-pdf',
    component: CreatePdfComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
