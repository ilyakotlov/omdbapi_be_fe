package com.test.movies.controller;

import com.test.movies.dto.SearchPageDto;
import com.test.movies.service.OmdbService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

	private final OmdbService omdb;

	@GetMapping("/movies")
	public Mono<SearchPageDto> movies(
			@RequestParam("q") String q,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return omdb.searchPaged(q, page);
	}
}