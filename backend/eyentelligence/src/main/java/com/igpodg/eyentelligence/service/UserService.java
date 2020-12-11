package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.UserDto;
import com.igpodg.eyentelligence.exception.EyenNotFoundException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private UserDto convertToUserDto(User user) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper.map(user, UserDto.class);
    }

    private List<UserDto> convertToUserDto(List<User> users) {
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    private User convertToUser(UserDto userDto) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper.map(userDto, User.class);
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
