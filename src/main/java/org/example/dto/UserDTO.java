package org.example.dto;

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
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private Role role;
    private Status status;

    private DepartmentDTO department;

}
