package org.example.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.DepartmentDTO;

import javax.persistence.Column;

@ApiModel(value = "Registration entity", description = "Contains fields for registration of employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private DepartmentDTO department;

}
