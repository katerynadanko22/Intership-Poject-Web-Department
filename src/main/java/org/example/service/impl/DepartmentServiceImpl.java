package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Department;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

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
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No such department in BD"));
    }

    @Override
    public Department update(Integer id, Department departmentNew) {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No such department in BD"));
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
