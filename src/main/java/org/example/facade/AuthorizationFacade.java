package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTORegistration;
import org.example.entity.ResetPassword;
import org.example.entity.User;
import org.example.modelmapper.UserMapper;
import org.example.service.AuthorizationService;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationFacade {
    private static AuthorizationService authorizationService;
    private static UserService userService;
    private static UserMapper mapper;

    @Transactional
    public UserDTORegistration registerUser(UserDTORegistration user) {
        return mapper.entityToRegistration(authorizationService.registerUser(mapper.registrationToEntity(user)));
    }

    @Transactional
    public List<UserDTORegistration> registerAll(List<User> users1) {
        List<User> users = authorizationService.registerAll(users1);
        return users
                .stream()
                .map(mapper::entityToRegistration)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTORegistration resetPassword(ResetPassword resetPassword) {
        return mapper.entityToRegistration(authorizationService.resetPassword(resetPassword));
    }
}
