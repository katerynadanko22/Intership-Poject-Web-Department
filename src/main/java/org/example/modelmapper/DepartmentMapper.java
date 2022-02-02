package org.example.modelmapper;

import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DepartmentMapper {
    @Autowired
    private ModelMapper mapper;


    public Department toEntity(DepartmentDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Department.class);
    }

    public DepartmentDTO toDto(Department entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DepartmentDTO.class);
    }
}
