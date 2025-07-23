package com.test.movies.controller;

import com.test.movies.dto.MovieSaveRequest;
import com.test.movies.dto.MovieSummaryDto;
import com.test.movies.service.FavMovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/favmovies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FavMovieController {

	private final FavMovieService service;

	@GetMapping
	public Flux<MovieSummaryDto> all() {
		return service.findAll()
				.map(f -> new MovieSummaryDto(f.getImdbId(), f.getTitle(), f.getYear()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> add(@RequestBody @Valid MovieSaveRequest body) {
		return service.save(body).then();
	}

	@DeleteMapping("/{imdbId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String imdbId) {
		return service.delete(imdbId);
	}
}