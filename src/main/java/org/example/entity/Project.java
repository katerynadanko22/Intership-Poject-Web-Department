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

    public Project(Long id) {
        this.id = id;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<ProjectPosition> getProjectPositions() {
        return projectPositions;
    }

    public void setProjectPositions(Set<ProjectPosition> projectPositions) {
        this.projectPositions = projectPositions;
    }
}
