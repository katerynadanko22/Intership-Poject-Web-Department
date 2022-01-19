package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<ProjectPosition> projectPositions;
}
