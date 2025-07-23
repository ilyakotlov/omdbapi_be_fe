package com.test.movies.service;

import com.test.movies.dto.MovieSaveRequest;
import com.test.movies.entity.FavMovie;
import com.test.movies.repository.FavMovieRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class FavMovieService {

  private final FavMovieRepository repo;

  public Flux<FavMovie> findAll() {
    return Flux.defer(() -> Flux.fromIterable(repo.findAll()))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<FavMovie> save(MovieSaveRequest r) {
    return Mono.fromCallable(() -> {
      if (repo.existsByImdbId(r.imdbId())) {
        throw new ResponseStatusException(
            HttpStatus.CONFLICT, "Movie already saved");
      }
      return repo.save(new FavMovie(null, r.imdbId(), r.title(), r.year()));
    })
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Void> delete(String imdbId) {
    return Mono.fromRunnable(() -> {
      if (!repo.existsByImdbId(imdbId)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Movie not found");
      }
      repo.deleteByImdbId(imdbId);
    })
        .subscribeOn(Schedulers.boundedElastic())
        .then();
  }
}