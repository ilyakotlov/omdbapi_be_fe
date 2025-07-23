package com.test.movies.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favmovies", indexes = @Index(name = "uk_favmovies_imdb", columnList = "imdbId", unique = true))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavMovie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String imdbId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String year;
}