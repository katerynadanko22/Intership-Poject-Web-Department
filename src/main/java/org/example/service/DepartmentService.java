package org.example.service;


import org.example.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department save(Department department);

    List<Department> findAll();

    Optional<Department> findById(Integer id);

    Department update(Department departmentNew);

    void deleteById(Integer id);

    Department getById(Integer id);
}
