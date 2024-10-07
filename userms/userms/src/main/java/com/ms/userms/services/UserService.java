package com.ms.userms.services;

import com.ms.userms.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserbyId(Integer userId);

    User updateUserById(User user,Integer userId);
}
