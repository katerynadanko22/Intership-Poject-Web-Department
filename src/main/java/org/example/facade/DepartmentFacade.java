package org.example.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.converter.DepartmentConverter;
//import org.example.mapper.DepartmentDtoMapper;
import org.example.dto.DepartmentDTO;
import org.example.entity.Department;
import org.example.exception.ValidationException;
import org.example.service.DepartmentService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentFacade {

//    private DepartmentDtoMapper mapper
//            = Mappers.getMapper(DepartmentDtoMapper.class);

    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    public DepartmentFacade(DepartmentService departmentService, DepartmentConverter departmentConverter) {
        this.departmentService = departmentService;
        this.departmentConverter = departmentConverter;
    }

    public DepartmentDTO save(DepartmentDTO departmentDTO) throws ValidationException {
        Department savedDep = departmentService.save(departmentConverter.fromDepartmentDTOToDepartment(departmentDTO));
        return departmentConverter.fromDepartmentToDepartmentDto(savedDep);
    }


    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentService.findAll();
        return departments
                .stream()
                .map(departmentConverter::fromDepartmentToDepartmentDto)
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> findById(Integer id) {
        return Optional.ofNullable(departmentConverter.
                fromDepartmentToDepartmentDto(departmentService.findById(id).get()));
    }

    public DepartmentDTO getById(Integer id) {
        return departmentConverter.fromDepartmentToDepartmentDto(departmentService.getById(id));
    }

//    public DepartmentDTO update(DepartmentDTO departmentDTONew) {
//        Department department = departmentService.findById(departmentDTONew.getId());
//        department.setTitle(departmentDTONew.getTitle());
//        return departmentConverter.fromDepartmentToDepartmentDto(departmentService.save(department));
//    }

    public void deleteById(Integer id) {
            departmentService.deleteById(id);
    }
}
