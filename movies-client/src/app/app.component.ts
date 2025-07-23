import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, MatToolbarModule, MatButtonModule],
  template: `
    <mat-toolbar color="primary" class="justify-between">
      <span>Movies</span>
      <span>
        <a mat-button routerLink="/">Search</a>
        <a mat-button routerLink="/favorites">Favourites</a>
      </span>
    </mat-toolbar>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}