package org.example.mapper;

import org.example.dto.UserDTO;
import org.example.entity.User;

public interface UserDtoMapper {
    UserDTO toUserDto(User user);

    User toUser(UserDTO userDTO);
}
