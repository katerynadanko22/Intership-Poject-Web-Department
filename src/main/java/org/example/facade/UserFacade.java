package org.example.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.modelmapper.UserMapper;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class UserFacade {
    private final UserService userService;
    private final DepartmentFacade departmentFacade;
    private final UserMapper mapper;
    @Autowired
    ModelMapper modelMapper;

    public UserFacade(UserService userService, DepartmentFacade departmentFacade,
                      UserMapper mapper) {
        this.userService = userService;
        this.departmentFacade = departmentFacade;
        this.mapper = mapper;
    }

    public UserDTO save(UserDTO userDTO) {
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName()));
            User savedUser = userService
                    .save(mapper.toEntity(userDTO));
            UserDTO savedUserDTO = mapper.toDto(savedUser);
            return savedUserDTO;
    }

    public UserDTO findById(Integer id) {
        return mapper.toDto(userService.findById(id).get());
    }

    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return users
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Integer id) {
        return mapper.toDto(userService.getById(id));
    }

    public UserDTO update(Integer id, UserDTO dto) {
        User updated = userService.update(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    public void deleteById(Integer id) {
        userService.deleteById(id);
    }

}
