package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.dto.ProjectDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.entity.Department;
import org.example.exception.ValidationException;
import org.example.facade.DepartmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @Autowired
    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    @PostMapping(value = "/save")
    private ResponseEntity<String> saveDepartmentDTO(@RequestBody DepartmentDTO departmentDTO) throws ValidationException {
        departmentFacade.save(departmentDTO);
        return ResponseEntity.ok("Department: " + departmentDTO + " has successfully saves!");
    }

    @Transactional
    @GetMapping("/get/{id}")
    ResponseEntity<String> getDepartmentDTOById(@PathVariable("id") Integer id) {
        DepartmentDTO departmentDTO = departmentFacade.getById(id);
        return ResponseEntity.ok("Department: " + departmentDTO);
    }

    @GetMapping("/find/{id}")
    private ResponseEntity<String> findDepartmentDTOById(@PathVariable("id") Integer id) {
        DepartmentDTO departmentDTO = departmentFacade.findById(id);
        return ResponseEntity.ok("Department: " + departmentDTO);
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllDepartmentsDTO() {
        List<DepartmentDTO> allDepartmentsDTO = departmentFacade.findAll();
        return ResponseEntity.ok("Departments: " + allDepartmentsDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateDepartmentDTOById(@PathVariable("id") Integer id,
                                                           @RequestBody DepartmentDTO departmentDTODetails)
            throws ResourceNotFoundException {
        DepartmentDTO updatedDepartmentDTO = departmentFacade.update(id, departmentDTODetails);
        return ResponseEntity.ok("Department " + updatedDepartmentDTO + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteDepartmentDTOById(@PathVariable("id") Integer id) {
        departmentFacade.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
