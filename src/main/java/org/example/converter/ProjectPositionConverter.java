package org.example.converter;

import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;
import org.springframework.stereotype.Component;

@Component
public class ProjectPositionConverter {
    public ProjectPosition fromProjectPositionDtoToProjectPosition(ProjectPositionDTO projectPositionDTO){
    ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(projectPositionDTO.getId());
        projectPosition.setProject(projectPositionDTO.getProject());
        projectPosition.setUser(projectPositionDTO.getUser());
        projectPosition.setPositionTitle(projectPositionDTO.getPositionTitle());
        projectPosition.setPositionStartDate(projectPositionDTO.getPositionStartDate());
        projectPosition.setPositionEndDate(projectPositionDTO.getPositionEndDate());
        return projectPosition;
}

    public ProjectPositionDTO fromProjectPositionToProjectPositionDto(ProjectPosition projectPosition) {
        return ProjectPositionDTO.builder()
                .id(projectPosition.getId())
                .project(projectPosition.getProject())
                .positionTitle(projectPosition.getPositionTitle())
                .user(projectPosition.getUser())
                .positionStartDate(projectPosition.getPositionStartDate())
                .positionEndDate(projectPosition.getPositionEndDate())
                .build();
    }
}
