package com.test.movies.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import com.test.movies.entity.FavMovie;

public interface FavMovieRepository extends JpaRepository<FavMovie, Long> {

	Optional<FavMovie> findByImdbId(String imdbId);

	@Modifying(clearAutomatically = true)
	@Transactional
	void deleteByImdbId(String imdbId);

	boolean existsByImdbId(String imdbId);
}