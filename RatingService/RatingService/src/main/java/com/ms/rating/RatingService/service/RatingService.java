package com.ms.rating.RatingService.service;

import com.ms.rating.RatingService.models.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(Integer userId);

    List<Rating> getRatingByMovieId(Integer movieId);
}
