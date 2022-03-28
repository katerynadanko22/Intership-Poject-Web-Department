package org.example.modelmapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ProjectMapper {

    private final ModelMapper mapper;

    public Project toEntity(ProjectDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Project.class);
    }

    public ProjectDTO toDto(Project entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProjectDTO.class);
    }
}
