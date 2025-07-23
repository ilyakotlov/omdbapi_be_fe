import { Component, signal } from '@angular/core';
import { MovieApiService, MovieSummary, SearchResponse } from '../core/movie-api.service';
import { NgForOf, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule }  from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';

@Component({
  standalone: true,
  selector: 'app-search-page',
  imports: [
    NgIf, NgForOf, FormsModule,
    MatInputModule, MatCardModule, MatButtonModule, MatPaginatorModule
  ],
  templateUrl: './search.page.html'
})
export class SearchPage {
  q = '';
  page = 1;
  readonly result = signal<SearchResponse | null>(null);
  readonly favIds = signal<Set<string>>(new Set());

  constructor(private api: MovieApiService) {
    this.api.getFavs().subscribe(arr => this.favIds.set(new Set(arr.map(a => a.imdbID))));
  }

  search() {
    this.api.search(this.q, this.page).subscribe(res => this.result.set(res));
  }

  onPage(e: PageEvent) {
    this.page = e.pageIndex + 1;
    this.search();
  }

  add(m: MovieSummary) {
    this.api.addFav(m).subscribe(() => this.favIds.update(s => s.add(m.imdbID)));
  }

  remove(m: MovieSummary) {
    this.api.removeFav(m.imdbID).subscribe(() => this.favIds.update(s => { s.delete(m.imdbID); return s; }));
  }

  inFav(id: string) { return this.favIds().has(id); }
}