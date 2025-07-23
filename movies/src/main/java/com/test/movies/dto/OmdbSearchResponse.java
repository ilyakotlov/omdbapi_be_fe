package com.test.movies.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;

public record OmdbSearchResponse(
    @JsonAlias("Search") List<Movie> search,
    @JsonAlias("totalResults") String totalResults,
    @JsonAlias("Response") String response) {
  public record Movie(
      @JsonAlias("Title") String title,
      @JsonAlias("Year") String year,
      @JsonAlias("imdbID") String imdbID,
      @JsonAlias("Type") String type,
      @JsonAlias("Poster") String poster) {
  }
}