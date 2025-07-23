import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface SearchResponse {
  items: MovieSummary[];
  total: number;
}

export interface MovieSummary {
  imdbID: string;
  title: string;
  year: string;
}

export interface SavePayload {
  title: string;
  imdbID: string;
  year: string;
}

@Injectable({ providedIn: 'root' })
export class MovieApiService {
  private http = inject(HttpClient);
  private base = environment.apiUrl;

  search(q: string, page = 1): Observable<SearchResponse> {
    const params = new HttpParams().set('q', q).set('page', page);
    return this.http.get<SearchResponse>(`${this.base}/movies`, { params });
  }

  getFavs(): Observable<MovieSummary[]> {
    return this.http.get<MovieSummary[]>(`${this.base}/favmovies`);
  }

  addFav(body: SavePayload) {
    return this.http.post<void>(`${this.base}/favmovies`, body);
  }

  removeFav(imdbId: string) {
    return this.http.delete<void>(`${this.base}/favmovies/${imdbId}`);
  }
}