package org.example.modelmapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
import org.example.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper mapper;

    public User toEntity(UserDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDTO toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDTO.class);
    }

    public User registrationToEntity(UserDTORegistration userDTORegistration) {
        return Objects.isNull(userDTORegistration) ? null : mapper.map(userDTORegistration, User.class);
    }

    public UserDTO registrationToDto(UserDTORegistration registration) {
        return Objects.isNull(registration) ? null : mapper.map(registration, UserDTO.class);
    }

    public UserDTORegistration dtoToRegistration(UserDTO userDTO) {
        return Objects.isNull(userDTO) ? null : mapper.map(userDTO, UserDTORegistration.class);
    }

    public UserDTORegistration entityToRegistration(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDTORegistration.class);
    }
}
