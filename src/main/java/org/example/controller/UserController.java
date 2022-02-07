package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.dto.UserDTORegistration;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.DepartmentFacade;
import org.example.facade.UserFacade;
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

@Api(value = "Swagger2DemoRestController", description = "REST Apis related to User Entity")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserFacade userFacade;
    private final DepartmentFacade departmentFacade;

    @ApiOperation(value = "Save new User in the System ", response = UserDTO.class, tags = "saveUser")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @PostMapping(value = "/{departmentId}")
    private UserDTO save(@RequestBody UserDTORegistration userDTORegistration,
                         @PathVariable("departmentId") Integer departmentId) {

        userDTORegistration.setDepartment(departmentFacade.findById(departmentId));
        return userFacade.save(userDTORegistration);
    }

    @ApiOperation(value = "Get specific User in the System ", response = UserDTO.class, tags = "getUser")
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    private UserDTO findById(@PathVariable("id") Integer id) {
        return userFacade.findById(id);
    }

    @ApiOperation(value = "Get list of Users in the System ", response = Iterable.class, tags = "getUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/")
    private List<UserDTO> getAll() {
        return userFacade.findAll();
    }

    @ApiOperation(value = "Update User in the System ", response = UserDTO.class, tags = "updateUser")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @PutMapping("/{id}")
    private UserDTO update(@PathVariable("id") Integer id, @RequestBody UserDTO dto)
            throws ResourceNotFoundException {
        return userFacade.update(id, dto);
    }

    @ApiOperation(value = "Delete User by id in the System", response = Integer.class, tags = "deleteUser")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        userFacade.deleteById(id);
        return "User: " + id + "deleted successfully";
    }
}













