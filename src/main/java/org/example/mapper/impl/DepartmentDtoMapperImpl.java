package org.example.mapper.impl;

import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.example.mapper.DepartmentDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDtoMapperImpl implements DepartmentDtoMapper {
    @Override
    public DepartmentDTO toDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setTitle(department.getTitle() );
        return departmentDTO;
    }

    @Override
    public Department toDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return null;
        }
        Department department = new Department();
        department.setTitle(departmentDTO.getTitle());
        return department;
    }
}