package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
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
    public UserDTO save(UserDTORegistration userDTORegistration) {
        User savedUser = userService.save(mapper.registrationToEntity(userDTORegistration));
        UserDTORegistration savedUserDTORegistration = mapper.entityToRegistration(savedUser);
        return mapper.registrationToDto(savedUserDTORegistration);
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
