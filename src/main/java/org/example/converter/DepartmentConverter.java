package org.example.converter;

import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {
    public Department fromDepartmentDTOToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setTitle(departmentDTO.getTitle());
        return department;
    }

    public DepartmentDTO fromDepartmentToDepartmentDto(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .title(department.getTitle())
                .build();
    }
}

