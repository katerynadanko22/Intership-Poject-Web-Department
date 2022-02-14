package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTORegistration;
import org.example.entity.RegistrationEntity;
import org.example.entity.ResetPassword;
import org.example.exception.InValidCSVException;
import org.example.facade.AuthorizationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Read users from CSV file", description = "REST Apis related to User Entity")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/authorization")
public class AuthorizationController {

    private static AuthorizationFacade authorizationFacade;
    private static BindingResultParser bindingResultParser;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register employee", response = UserDTORegistration.class)
    public UserDTORegistration registerEmployee(@ApiParam(name = "Registration request", value = "Contains mandatory fields for registration of employee")
                                        @RequestBody
                                        @Valid RegistrationEntity registration, BindingResult result) {
        log.info("executing registerEmployee() method");
        if (result.hasErrors()) {
            throw new InValidCSVException
                    (String.format("Fields of Employee have errors: %s", bindingResultParser.getFieldErrMismatches(result)));
        }
        return authorizationFacade.registerUser(registration.toDTO());
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Reset password of employee")
    public void resetPassword(@ApiParam(name = "Reset password request", value = "Contains username, old and new password of employee")
                              @RequestBody
                              @Valid ResetPassword request, BindingResult result) {
        log.info("executing resetPassword() method");
        if (result.hasErrors()) {
            throw new InValidCSVException("Fields have errors: " + bindingResultParser.getFieldErrMismatches(result));
        }
        authorizationFacade.resetPassword(request);
    }
}
