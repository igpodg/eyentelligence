package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.EyenNotFoundException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(EyenNotFoundException::new);
    }

    public User saveUser(User user) {
        this.userRepository.deleteAll();
        user.setId(1);
        return this.userRepository.save(user);
    }
}
