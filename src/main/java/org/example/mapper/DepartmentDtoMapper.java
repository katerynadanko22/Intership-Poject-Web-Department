package org.example.mapper;

import org.example.dto.DepartmentDTO;
import org.example.entity.Department;

public interface DepartmentDtoMapper {

    DepartmentDTO toDepartmentDto(Department department);

    Department toDepartment(DepartmentDTO departmentDTO);
}
