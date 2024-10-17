package com.ms.userms.Controller;

import com.ms.userms.models.User;
import com.ms.userms.repository.UserRepository;
import com.ms.userms.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    int count=1;
    @PostMapping("/saveUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingMovieBreaker",fallbackMethod = "ratingMovieFallback")
    @Retry(name = "ratingMovieService")
    public ResponseEntity<User> getSingleUser(@PathVariable Integer userId){
        logger.info("req_count:"+count);
        count++;
        User user = userService.getUserbyId(userId);
        return ResponseEntity.ok(user);
    }

    //fallback for circuitbreaker
    public ResponseEntity<User> ratingMovieFallback(Integer userId,Exception ex){
        logger.info("Fallback executed because service is down",ex.getMessage());
        User user =User.builder().email("dummy@gmail.com").name("Dummy").about("Dummy user as movie service failed").id(12345).build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
