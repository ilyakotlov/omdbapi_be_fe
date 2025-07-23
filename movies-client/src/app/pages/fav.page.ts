import { Component, signal } from '@angular/core';
import { MovieApiService, MovieSummary } from '../core/movie-api.service';
import { NgIf, NgForOf } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  standalone: true,
  selector: 'app-fav-page',
  imports: [NgIf, NgForOf, MatCardModule, MatIconModule, MatButtonModule],
  templateUrl: './fav.page.html'
})
export class FavPage {
  readonly favs = signal<MovieSummary[]>([]);

  constructor(private api: MovieApiService) { this.refresh(); }

  refresh() { this.api.getFavs().subscribe(this.favs.set); }

  remove(id: string) {
    this.api.removeFav(id).subscribe(() => this.refresh());
  }
}