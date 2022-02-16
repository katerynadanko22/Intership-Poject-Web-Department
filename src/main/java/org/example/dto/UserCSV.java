package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCSV {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String jobTitle;

}
