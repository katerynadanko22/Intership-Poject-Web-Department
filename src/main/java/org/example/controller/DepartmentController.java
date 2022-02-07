package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DepartmentDTO;
import org.example.facade.DepartmentFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Department Entity")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @ApiOperation(value = "Save new Department in the System ", response = DepartmentDTO.class, tags = "saveDepartment")
    @PostMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('read','write')")
    public DepartmentDTO save(@RequestBody DepartmentDTO departmentDTO) {
        return departmentFacade.save(departmentDTO);
    }

    @ApiOperation(value = "Get specific Department in the System ", response = DepartmentDTO.class, tags = "getDepartment")
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    private DepartmentDTO findById(@PathVariable("id") Integer id) {
        return departmentFacade.findById(id);
    }

    @ApiOperation(value = "Get list of Departments in the System ", response = Iterable.class, tags = "getDepartments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/")
    private List<DepartmentDTO> getAll() {
        return departmentFacade.findAll();
    }

    @ApiOperation(value = "Update Department in the System ", response = DepartmentDTO.class, tags = "updateDepartment")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('read','write')")
    private DepartmentDTO update(@PathVariable("id") Integer id, @RequestBody DepartmentDTO departmentDTODetails) {
        return departmentFacade.update(id, departmentDTODetails);
    }

    @ApiOperation(value = "Delete Department by id in the System", response = Integer.class, tags = "deleteDepartment")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('read','write')")
    private String delete(@PathVariable("id") Integer id) {
        departmentFacade.deleteById(id);
        return "Department: " + id + "deleted successfully";
    }
}
