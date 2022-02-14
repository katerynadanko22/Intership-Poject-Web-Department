package org.example.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.UserDTORegistration;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Registration entity", description = "Contains fields for registration of employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationEntity {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String jobTitle;

    @NotBlank
    private Role role;

    public UserDTORegistration toDTO() {
        return UserDTORegistration.builder()
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .role(role)
                .jobTitle(jobTitle)
                .email(email)
                .build();
    }
}
