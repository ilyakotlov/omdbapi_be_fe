package com.test.movies.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.test.movies.dto.MovieSummaryDto;
import com.test.movies.dto.OmdbSearchResponse;
import com.test.movies.dto.SearchPageDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OmdbService {

	private final WebClient omdbWebClient;

	@Value("${omdb.api-key}")
	private String apiKey;

	public Mono<SearchPageDto> searchPaged(String q, int page) {
		return omdbWebClient.get()
				.uri(uri -> uri.queryParam("apikey", apiKey)
						.queryParam("s", q)
						.queryParam("page", page)
						.build())
				.retrieve()
				.bodyToMono(OmdbSearchResponse.class)
				.flatMap(resp -> {
					if (!"True".equalsIgnoreCase(resp.response())) {
						return Mono.error(new ResponseStatusException(
								HttpStatus.NOT_FOUND, resp.response()));
					}
					List<MovieSummaryDto> items = resp.search() != null
							? resp.search().stream()
									.map(it -> new MovieSummaryDto(
											it.imdbID(), it.title(), it.year()))
									.toList()
							: List.of();
					int total = Integer.parseInt(resp.totalResults());
					return Mono.just(new SearchPageDto(items, total));
				})
				.timeout(Duration.ofSeconds(3));
	}
}