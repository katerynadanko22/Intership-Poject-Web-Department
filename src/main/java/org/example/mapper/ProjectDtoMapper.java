package org.example.mapper;

import org.example.dto.ProjectDTO;
import org.example.entity.Project;

public interface ProjectDtoMapper {
    ProjectDTO toProjectDto(Project project);

    Project toProject(ProjectDTO projectDTO);
}
