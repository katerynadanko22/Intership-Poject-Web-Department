package org.example.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.dto.ProjectDTO;
import org.example.entity.Department;
import org.example.entity.Project;
import org.example.modelmapper.DepartmentMapper;
import org.example.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentFacade {

    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    public DepartmentFacade(DepartmentService departmentService, DepartmentMapper mapper) {
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department entity = mapper.toEntity(departmentDTO);
        Department saved = departmentService.save(entity);
        return mapper.toDto(saved);
    }

    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentService.findAll();
        return departments
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartmentDTO findById(Integer id) {
        return mapper.toDto(departmentService.findById(id).get());
    }

    public DepartmentDTO getById(Integer id) {
        return mapper.toDto(departmentService.getById(id));
    }

    public DepartmentDTO update(Integer id, DepartmentDTO departmentDTONew) {
        Department update = departmentService.update(id, mapper.toEntity(departmentDTONew));
        return mapper.toDto(update);
    }

    public void deleteById(Integer id) {
        departmentService.deleteById(id);
    }
}
