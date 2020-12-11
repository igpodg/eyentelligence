package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.UserDto;
import com.igpodg.eyentelligence.exception.EyenNotFoundException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPasswordHash(user.getPasswordHash());
        userDto.setTitle(user.getTitle());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAvatarLink(user.getAvatarLink());
        return userDto;
    }

    private List<UserDto> convertToUserDto(List<User> users) {
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    private <T> T unwrap(Optional<T> value) {
        return (value == null) ? null : value.orElse(null);
    }

    private User convertToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(unwrap(userDto.getUsername()));
        user.setPasswordHash(unwrap(userDto.getPasswordHash()));
        user.setTitle(unwrap(userDto.getTitle()));
        user.setFirstName(unwrap(userDto.getFirstName()));
        user.setMiddleName(unwrap(userDto.getMiddleName()));
        user.setLastName(unwrap(userDto.getLastName()));
        user.setEmail(unwrap(userDto.getEmail()));
        user.setAvatarLink(unwrap(userDto.getAvatarLink()));
        return user;
    }

    private List<User> convertToUser(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(this::convertToUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllUsers() {
        return this.convertToUserDto(this.userRepository.findAll());
    }

    public UserDto getUserById(Integer id) {
        //return this.userRepository.findById(id)
        //        .orElseThrow(EyenNotFoundException::new);

        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new EyenNotFoundException();

        return this.convertToUserDto(user.get());
    }

    public UserDto saveUser(UserDto user) {
        //this.userRepository.deleteAll();
        //user.setId(1);
        //return this.userRepository.save(user);

        this.userRepository.deleteAll();
        user.setId(1);
        User saved = this.convertToUser(user);
        return this.convertToUserDto(saved);
    }
}
