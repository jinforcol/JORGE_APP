import { Routes } from '@angular/router';
import { Calculadora } from './component/calculadora/calculadora';
import {  Policy } from './component/policy/policy';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'calculadora',
    pathMatch: 'full'
  },
  {
    path: 'calculadora',
    component: Calculadora
  },
    {
    path: 'policy',
    component: Policy
  },
  {
    path: '**',
    redirectTo: 'calculadora'
  }
];