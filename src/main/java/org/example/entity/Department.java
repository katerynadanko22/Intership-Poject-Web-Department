package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<User> users;
}
