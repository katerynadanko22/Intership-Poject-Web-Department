package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.example.modelmapper.DepartmentMapper;
import org.example.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class DepartmentFacade {

    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    @Transactional
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
        return mapper.toDto(departmentService.findById(id));
    }

    @Transactional
    public DepartmentDTO update(Integer id, DepartmentDTO departmentDTONew) {
        Department update = departmentService.update(id, mapper.toEntity(departmentDTONew));
        return mapper.toDto(update);
    }

    @Transactional
    public void deleteById(Integer id) {
        departmentService.deleteById(id);
    }
}
