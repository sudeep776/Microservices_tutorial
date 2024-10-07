package com.ms.movie.movieService.Controller;

import com.ms.movie.movieService.models.Movie;
import com.ms.movie.movieService.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/createMovie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie movie1= movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie1);
    }

    @GetMapping("/{movieId}")
    private ResponseEntity<Movie> getMovieById(@PathVariable Integer movieId){
        Movie movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/getAllMovies")
    private ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        return ResponseEntity.ok(movieList);
    }
}
