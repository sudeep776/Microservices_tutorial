package com.ms.movie.movieService.repository;

import com.ms.movie.movieService.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
