package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
import org.example.entity.Department;
import org.example.entity.ResetPassword;
import org.example.entity.User;
import org.example.modelmapper.UserMapper;
import org.example.repository.DepartmentRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserFacade {
    private final UserService userService;
    private final UserMapper mapper;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public UserDTO registerUser(UserDTORegistration userDTORegistration) {
        User user = mapper.registrationToEntity(userDTORegistration);

        Integer departmentId = userDTORegistration.getDepartment().getId();
        Department department = Optional.ofNullable(departmentRepository.getOne(departmentId)).orElseThrow(() -> new NoSuchElementException("No department in DB with id {}" + departmentId));

        user.setDepartment(department);

        User savedUser = userService.registerUser(user);
        UserDTORegistration savedUserDTORegistration = mapper.entityToRegistration(savedUser);
        UserDTO userDTO = mapper.registrationToDto(savedUserDTORegistration);
        return userDTO;
    }

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
        User updated = userService.update(mapper.toEntity(dto), id);
        return mapper.toDto(updated);
    }

    @Transactional
    public UserDTO updateDepartment(Integer newDepartmentId, Integer id) {
        User updated = userService.updateDepartment(newDepartmentId, id);
        return mapper.toDto(updated);
    }

    @Transactional
    public void deleteById(Integer id) {
        userService.deleteById(id);
    }
}
