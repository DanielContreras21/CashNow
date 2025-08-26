import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [

  {
    path:'home',
    loadChildren: () =>import('./modules/home/home.module').then(m => m.HomeModule),
  },
  {
    path:'auth',
    loadChildren: () =>import('./modules/auth/auth.module').then(m => m.AuthModule),
  },

  {path: '',  redirectTo: 'home',pathMatch: 'full'}

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: false}),


  ],
  exports: [RouterModule],
  providers: [RouterModule]
})
export class AppRoutingModule { }
