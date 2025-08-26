import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';




const routes:Routes =[
  {
    path:'',
    children:[

      {path:'register', component: RegisterComponent},
      {path:'login', component: LoginComponent},
      {path: '',  redirectTo: 'login', pathMatch: 'full'}

    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
