package org.example.modelmapper;

import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectPositionMapper {
    @Autowired
    private ModelMapper mapper;

    public ProjectPosition toEntity(ProjectPositionDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ProjectPosition.class);
    }

    public ProjectPositionDTO toDto(ProjectPosition entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProjectPositionDTO.class);
    }
}
