package com.ms.userms.services;

import com.ms.userms.exceptions.ResourceNotFoundException;
import com.ms.userms.models.User;
import com.ms.userms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList ratingsOfUser =restTemplate.getForObject("http://localhost:8083/ratings/users/"+userId, ArrayList.class);
//        logger.info("{}",ratingsOfUser);
        user.setRatings(ratingsOfUser);
        return user;
    }

    @Override
    public User updateUserById(User user, Integer userId) {
        User userUpdate = userRepository.findById(userId).orElse(null);
        return userRepository.save(userUpdate);
    }
}
