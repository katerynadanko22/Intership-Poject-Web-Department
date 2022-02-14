package org.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Role;
import org.example.entity.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    @ApiModelProperty(notes = "First name of the User", name = "First name", required = true, value = "Kate")
    private String firstName;
    @ApiModelProperty(notes = "Last name of the User", name = "Last name", required = true, value = "Danko")
    private String lastName;
    @ApiModelProperty(notes = "email name of the User", name = "email", required = true, value = "user@mail.com")
    private String email;
    @ApiModelProperty(notes = "Job title  of the User", name = "Job title", required = true, value = "Middle")
    private String jobTitle;
    @ApiModelProperty(notes = "role of the User", name = "role", required = true, value = "ROLE_USER")
    private Role role;
    @ApiModelProperty(notes = "status of the User", name = "status", required = true, value = "ACTIVE")
    private Status status;
    @ApiModelProperty(notes = "department of the User", name = "department", required = true, value = "1")
    private DepartmentDTO department;

}



