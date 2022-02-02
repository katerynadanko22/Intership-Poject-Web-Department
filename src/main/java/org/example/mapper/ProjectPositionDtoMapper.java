package org.example.mapper;

import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;

public interface ProjectPositionDtoMapper {
    ProjectPositionDTO toProjectPositionDto(ProjectPosition projectPosition);
    ProjectPosition toProjectPosition(ProjectPositionDTO projectPositionDTO);
}
