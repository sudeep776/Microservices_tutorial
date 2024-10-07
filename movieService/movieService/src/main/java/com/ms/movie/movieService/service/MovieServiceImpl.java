package com.ms.movie.movieService.service;

import com.ms.movie.movieService.exceptions.ResourceNotFoundException;
import com.ms.movie.movieService.models.Movie;
import com.ms.movie.movieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new ResourceNotFoundException("movie not found with"+movieId));
        return movieRepository.save(movie);
    }
}
