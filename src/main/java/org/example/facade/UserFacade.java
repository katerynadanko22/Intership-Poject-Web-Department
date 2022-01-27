package org.example.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.converter.UserConverter;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.exception.ValidationException;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserFacade {
    private final UserService userService;
    private final UserConverter userConverter;

    public UserFacade(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    public UserDTO save(UserDTO userDTO, Integer departmentId) throws ValidationException {
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName()));
        validateUserDTO(userDTO);
        User savedUserDTO = userService.save(userConverter.fromUserDtoToUser(userDTO), departmentId);
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                savedUserDTO.getId(), savedUserDTO.getFirstName(), savedUserDTO.getLastName()));
        return userConverter.fromUserToUserDto(savedUserDTO);
    }

    public UserDTO save(UserDTO userDTO) throws ValidationException {
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName()));
        validateUserDTO(userDTO);
        User savedUserDTO = userService.save(userConverter.fromUserDtoToUser(userDTO));
        log.info(String.format("user.save {id = %d, firstName = %s, lastName = %s}",
                savedUserDTO.getId(), savedUserDTO.getFirstName(), savedUserDTO.getLastName()));
        return userConverter.fromUserToUserDto(savedUserDTO);
    }

    private void validateUserDTO(UserDTO userDto) throws ValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<UserDTO>> constraintViolation = validator.validateProperty(userDto, "email");
        if (!constraintViolation.isEmpty()) {
            throw new ValidationException("Incorrect Email format");
        }
        constraintViolation = validator.validateProperty(userDto, "password");

        if (!constraintViolation.isEmpty()) {
            throw new ValidationException("Incorrect Password format");
        }
    }


    public Optional<UserDTO> findById(Integer id) {
        return Optional.ofNullable(userConverter.fromUserToUserDto(userService.findById(id).get()));
    }

    public UserDTO getById(Integer id) {
        return userConverter.fromUserToUserDto(userService.getById(id));
    }

    public UserDTO update(UserDTO updatedUserDTO) {
        User savedUserDTOInForm = userService.save(userConverter.fromUserDtoToUser(updatedUserDTO));
        return userConverter.fromUserToUserDto(userService.update(savedUserDTOInForm));
    }

    public void deleteById(Integer id) {
//        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
//        }
    }
}
