package org.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Role;
import org.example.entity.Status;

import javax.validation.constraints.NotBlank;

@ApiModel(value = " User Registration entity", description = "Contains fields for registration of employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORegistration {
    @NotBlank
    private Integer id;
    @NotBlank
    @ApiModelProperty(notes = "First name of the User", name = "First name", required = true, value = "Kate")
    private String firstName;
    @NotBlank
    @ApiModelProperty(notes = "Last name of the User", name = "Last name", required = true, value = "Danko")
    private String lastName;
    @NotBlank
    @ApiModelProperty(notes = "email name of the User", name = "email", required = true, value = "user@mail.com")
    private String email;
    @NotBlank
    @ApiModelProperty(notes = "password name of the User", name = "password", required = true, value = "user")
    private String password;
    @NotBlank
    @ApiModelProperty(notes = "Job title  of the User", name = "Job title", required = true, value = "Middle")
    private String jobTitle;
    @NotBlank
    @ApiModelProperty(notes = "role of the User", name = "role", required = true, value = "ROLE_USER")
    private Role role;
    @NotBlank
    @ApiModelProperty(notes = "status of the User", name = "status", required = true, value = "ACTIVE")
    private Status status;
    @NotBlank
    @ApiModelProperty(notes = "department of the User", name = "department", required = true, value = "1")
    private DepartmentDTO department;


}




