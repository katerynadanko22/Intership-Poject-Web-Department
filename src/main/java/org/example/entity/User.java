package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "department")
    private String department;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ProjectPosition> projectPositions;

}
