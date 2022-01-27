package org.example.mapper;

import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.springframework.stereotype.Component;

public interface ProjectDtoMapper {
    ProjectDTO toProjectDto(Project project);
    Project toProject(ProjectDTO projectDTO);
}
