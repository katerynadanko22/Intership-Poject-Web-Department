package org.example.mapper.impl;

import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.example.mapper.ProjectDtoMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ProjectDtoMapperImpl implements ProjectDtoMapper {
    @NotNull
    @Override
    public ProjectDTO toProjectDto(Project project) {

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle(project.getTitle());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        return projectDTO;
    }

    @NotNull
    @Override
    public Project toProject(ProjectDTO projectDTO) {

        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        return project;
    }
}
