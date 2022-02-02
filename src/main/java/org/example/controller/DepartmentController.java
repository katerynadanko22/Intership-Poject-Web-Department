package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.facade.DepartmentFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @PostMapping(value = "/")
    public DepartmentDTO save(@RequestBody DepartmentDTO departmentDTO) {
        return departmentFacade.save(departmentDTO);
    }

    @GetMapping("/{id}")
    private DepartmentDTO findById(@PathVariable("id") Integer id) {
        return departmentFacade.findById(id);
    }

    @GetMapping(value = "/")
    private List<DepartmentDTO> getAll() {
        return departmentFacade.findAll();
    }

    @PutMapping("/{id}")
    private DepartmentDTO update(@PathVariable("id") Integer id, @RequestBody DepartmentDTO departmentDTODetails) {
        return  departmentFacade.update(id, departmentDTODetails);
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        departmentFacade.deleteById(id);
        return "Department: " + id + "deleted successfully";
    }
}
