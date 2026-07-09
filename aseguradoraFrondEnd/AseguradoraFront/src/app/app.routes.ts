import { Routes } from '@angular/router';
import { Calculadora } from './component/calculadora/calculadora';

export const routes: Routes = [
    { path: '', redirectTo: 'calculadora', pathMatch: 'full' },
    { path: 'calculadora', component: Calculadora },
    { path: '**', redirectTo: 'calculadora' },
];
