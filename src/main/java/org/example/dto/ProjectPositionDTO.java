package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Project;
import org.example.entity.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPositionDTO {
    private Integer id;
    private String positionTitle;
    private LocalDate positionStartDate;
    private LocalDate positionEndDate;
    private User user;
    private Project project;
}
