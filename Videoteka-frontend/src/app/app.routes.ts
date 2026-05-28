import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Dashboard } from './pages/dashboard/dashboard';
import { Filmovi } from './pages/filmovi/filmovi';
import { Primerci } from './pages/primerci/primerci';
import { Prodaja } from './pages/prodaja/prodaja';
import { Admin } from './pages/admin/admin';
import { authGuard } from './core/guards/auth-guard';
import { adminGuard } from './core/guards/admin-guard';
import { Clanovi } from './pages/clanovi/clanovi';

export const routes: Routes = [
    {path:'', redirectTo: 'login', pathMatch: 'full'},
    {path:'login',component: Login},
    {path:'dashboard', component:Dashboard, canActivate: [authGuard]},
    {path:'filmovi',component:Filmovi},
    {path:'primerci',component:Primerci, canActivate: [authGuard]},
    {path:'clanovi',component:Clanovi, canActivate: [authGuard]},
    {path:'admin',component:Admin, canActivate: [authGuard,adminGuard]},
    {path:'**', redirectTo: 'login'}
];
