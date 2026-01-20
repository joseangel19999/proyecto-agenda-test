import { Routes } from '@angular/router';
import { DefaultComponent } from './layout/default/default.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { ActividadComponent } from './pages/actividad/actividad.component';
import { DashboardComponent } from './layout/dashboard/dashboard.component';

export const routes: Routes = [

  {
    path:'',
    redirectTo:'login',
    pathMatch: 'full'
  },{
    path:'login',
    title: 'login',
    component:DefaultComponent,
    children:[{path:'',component:LoginComponent}]
  },{
    path:'home',
    title:'Dashboard',
    component:DashboardComponent,
    children:[{path:'',component:HomeComponent}]
  },
  {
    path:'actividad',
    title:'Dashboard',
    component:DashboardComponent,
    children:[{path:'',component:ActividadComponent}]
  }
];
