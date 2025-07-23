package com.test.movies.dto;

import java.util.List;

public record SearchPageDto(
		List<MovieSummaryDto> items,
		int total) {
}