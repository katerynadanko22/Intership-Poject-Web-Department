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
}
