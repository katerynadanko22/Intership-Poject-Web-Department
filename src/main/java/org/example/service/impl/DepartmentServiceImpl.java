package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Department;
import org.example.exception.DuplicateEntityException;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        log.info("department start to save with id{} ", department.getId());
        if (departmentRepository.existsByTitle(department.getTitle()))
            throw new DuplicateEntityException("Department already exist");
        log.error("department already exist with id{} ", department.getId());
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        log.info("department start to get All");
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        log.info("department start to get by id {} ", id);
        return departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such department in BD"));
    }

    @Override
    public Department update(Integer id, Department departmentNew) {
        log.info("department start to update  by id {} ", id);
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such department in BD"));
        department.setTitle(departmentNew.getTitle());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("department start to delete  by id {} ", id);
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
    }
}





