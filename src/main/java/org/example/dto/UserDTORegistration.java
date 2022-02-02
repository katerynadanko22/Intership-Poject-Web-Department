package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORegistration {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String jobTitle;
    private DepartmentDTO department;

}

