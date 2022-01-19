package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
