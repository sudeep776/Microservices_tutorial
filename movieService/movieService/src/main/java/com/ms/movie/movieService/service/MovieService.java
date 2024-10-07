package com.ms.movie.movieService.service;

import com.ms.movie.movieService.models.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();

    Movie getMovieById(Integer movieId);
}
