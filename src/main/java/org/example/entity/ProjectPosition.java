package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_positions")
public class ProjectPosition {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "position_title")
    private String positionTitle;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "position_start_date")
    private String positionStartDate;
    @Column(name = "position_end_date")
    private String positionEndDate;

    public ProjectPosition(Long id) {
        this.id = id;
    }

    public ProjectPosition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPositionStartDate() {
        return positionStartDate;
    }

    public void setPositionStartDate(String positionStartDate) {
        this.positionStartDate = positionStartDate;
    }

    public String getPositionEndDate() {
        return positionEndDate;
    }

    public void setPositionEndDate(String positionEndDate) {
        this.positionEndDate = positionEndDate;
    }
}
