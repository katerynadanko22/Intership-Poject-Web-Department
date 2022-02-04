package org.example.controller;

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

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserFacade userFacade;
    private final DepartmentFacade departmentFacade;

    @PreAuthorize("hasAuthority('write')")
    @PostMapping(value = "/{departmentId}")
    private UserDTO save(@RequestBody UserDTORegistration userDTORegistration,
                         @PathVariable("departmentId") Integer departmentId) {

        userDTORegistration.setDepartment(departmentFacade.findById(departmentId));
        return userFacade.save(userDTORegistration);
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    private UserDTO findById(@PathVariable("id") Integer id) {
        return userFacade.findById(id);
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/")
    private List<UserDTO> getAll() {
        return userFacade.findAll();
    }

    @PreAuthorize("hasAuthority('write')")
    @PutMapping("/{id}")
    private UserDTO update(@PathVariable("id") Integer id, @RequestBody UserDTO dto)
            throws ResourceNotFoundException {
        return userFacade.update(id, dto);
    }

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        userFacade.deleteById(id);
        return "User: " + id + "deleted successfully";
    }
}



