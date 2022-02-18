package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
import org.example.entity.ResetPassword;
import org.example.entity.User;
import org.example.modelmapper.UserMapper;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserFacade {
    private final UserService userService;
    private final UserMapper mapper;

    @Transactional
    public UserDTO registerUser(UserDTORegistration userDTORegistration, Integer departmentId) {
        User savedUser = userService.registerUser(mapper.registrationToEntity(userDTORegistration), departmentId);
        UserDTORegistration savedUserDTORegistration = mapper.entityToRegistration(savedUser);
        return mapper.registrationToDto(savedUserDTORegistration);
    }

//    @Transactional
//    public List<UserDTORegistration> registerAll(List<User> users1) {
//        List<User> users = userService.registerAll(users1);
//        return users
//                .stream()
//                .map(mapper::entityToRegistration)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public UserDTORegistration resetPassword(ResetPassword resetPassword) {
        return mapper.entityToRegistration(userService.resetPassword(resetPassword));
    }

    public UserDTO findById(Integer id) {
        return mapper.toDto(userService.findById(id));
    }

    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return users
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO update(Integer id, UserDTO dto) {
        User updated = userService.update(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Transactional
    public void deleteById(Integer id) {
        userService.deleteById(id);
    }

}
