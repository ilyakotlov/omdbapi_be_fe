import { Routes } from '@angular/router';
export const routes: Routes = [
  { path: '', loadComponent: () => import('./pages/search.page').then(m => m.SearchPage) },
  { path: 'favorites', loadComponent: () => import('./pages/fav.page').then(m => m.FavPage) },
  { path: '**', redirectTo: '' }
];