package org.example.converter;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDepartment(userDTO.getDepartment());
        user.setJobTitle(userDTO.getJobTitle());
        return user;
    }

    public UserDTO fromUserToUserDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .department(user.getDepartment())
                .jobTitle(user.getJobTitle())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
