package org.example.mapper.impl;

import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.example.mapper.ProjectDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoMapperImpl implements ProjectDtoMapper {
    @Override
    public ProjectDTO toProjectDto(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle(project.getTitle());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        return projectDTO;
    }

    @Override
    public Project toProject(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        return project;
    }
}
