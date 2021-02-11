package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.UserDto;
import com.igpodg.eyentelligence.exception.EyenUserException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.repository.UserRepository;
import com.igpodg.eyentelligence.util.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final static String ERR_USER_NOT_FOUND =
            "The specified user has not been found.";

    public List<UserDto> getAllUsers() {
        return DtoConversion.convertToUserDto(this.userRepository.findAll());
    }

    public UserDto getUserById(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new EyenUserException(ERR_USER_NOT_FOUND);

        return DtoConversion.convertToUserDto(user.get());
    }

    public UserDto saveUser(UserDto user) {
        this.userRepository.deleteAll();
        user.setId(1);
        User saved = this.userRepository.save(DtoConversion.convertToUser(user));
        return DtoConversion.convertToUserDto(saved);
    }
}
