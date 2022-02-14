package org.example.modelmapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class DepartmentMapper {

    private final ModelMapper mapper;


    public Department toEntity(DepartmentDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Department.class);
    }

    public DepartmentDTO toDto(Department entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DepartmentDTO.class);
    }
}
