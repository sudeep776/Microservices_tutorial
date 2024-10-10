package com.ms.userms.external_services;

import com.ms.userms.models.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="MOVIE-SERVICE")
public interface MovieService {
    @GetMapping("/movies/{movieId}")
    Movie getMovie(@PathVariable Integer movieId);
}
