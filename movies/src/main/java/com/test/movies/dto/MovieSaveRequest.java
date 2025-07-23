package com.test.movies.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record MovieSaveRequest(
		@JsonAlias("imdbID") @NotBlank String imdbId,
		@NotBlank String title,
		@NotBlank String year) {
}