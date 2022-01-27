package org.example.converter;

import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public Project fromProjectDTOToProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setTitle(projectDTO.getTitle());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        return project;
    }

    public ProjectDTO fromProjectToProjectDto(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }
}
