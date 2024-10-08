package com.ms.userms.services;

import com.ms.userms.exceptions.ResourceNotFoundException;
import com.ms.userms.models.Movie;
import com.ms.userms.models.Rating;
import com.ms.userms.models.User;
import com.ms.userms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private RestTemplate restTemplate;
    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserbyId(Integer userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found for : "+userId));
        Rating[] ratingsOfUser =restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
//        logger.info("{}",ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating->{
            ResponseEntity<Movie> movie = restTemplate.getForEntity("http://MOVIE-SERVICE/movies/"+rating.getMovieId(), Movie.class);
            Movie movie1 = movie.getBody();
            rating.setMovie(movie1);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUserById(User user, Integer userId) {
        User userUpdate = userRepository.findById(userId).orElse(null);
        return userRepository.save(userUpdate);
    }
}
