package org.example.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.entity.Department;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        log.info(String.format("department.save {id = %d, title = %s}", department.getId(),
                department.getTitle()));
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        log.info("department.getAll: " + departmentRepository.findAll());
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        if (!departmentRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return departmentRepository.findById(id);
    }

    @Override
    public Department getById(Integer id) {
        if (!departmentRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return departmentRepository.getById(id);
    }

    @Override
    public Department update(Integer id, Department departmentNew) {
        if (!departmentRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        Department department = departmentRepository.findById(id).get();
        department.setTitle(departmentNew.getTitle());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        if (departmentRepository.findById(id).isPresent()) {
        departmentRepository.deleteById(id);
        }
    }
}
