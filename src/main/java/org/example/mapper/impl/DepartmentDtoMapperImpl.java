package org.example.mapper.impl;

import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.example.mapper.DepartmentDtoMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class DepartmentDtoMapperImpl implements DepartmentDtoMapper {
    @NotNull
    @Override
    public DepartmentDTO toDepartmentDto(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setTitle(department.getTitle());
        return departmentDTO;
    }

    @NotNull
    @Override
    public Department toDepartment(DepartmentDTO departmentDTO) {

        Department department = new Department();
        department.setTitle(departmentDTO.getTitle());
        return department;
    }
}