package com.ms.rating.RatingService.repository;

import com.ms.rating.RatingService.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //custom finder methods
    List<Rating> findByUserId(Integer userId);
    List<Rating> findByMovieId(Integer movieId);

}
