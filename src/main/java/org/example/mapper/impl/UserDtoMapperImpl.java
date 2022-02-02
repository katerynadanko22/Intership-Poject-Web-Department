//package org.example.mapper.impl;
//
//import org.example.dto.UserDTO;
//import org.example.entity.User;
//import org.example.mapper.UserDtoMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDtoMapperImpl implements UserDtoMapper {
//    @Override
//    public UserDTO toUserDto(User user) {
//        if (user == null) {
//            return null;
//        }
//        UserDTO userDTO = new UserDTO();
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setDepartment(user.getDepartment());
//        userDTO.setJobTitle(user.getJobTitle());
//        return userDTO;
//    }
//
//    @Override
//    public User toUser(UserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//        User user = new User();
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setDepartment(userDTO.getDepartment());
//        user.setJobTitle(userDTO.getJobTitle());
//        return user;
//    }
//}
