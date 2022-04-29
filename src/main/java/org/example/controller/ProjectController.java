package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;
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

@Api(value = "Swagger2DemoRestController", tags = {"REST Apis related to Project Entity"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectFacade projectFacade;

    @ApiOperation(value = "Save new Project in the System ", response = ProjectDTO.class, tags = "saveProject")
    @PostMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('read','write')")
    private ProjectDTO save(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProjectDTO = projectFacade.save(projectDTO);
        return savedProjectDTO;
    }

    @ApiOperation(value = "Get list of Projects in the System ", response = Iterable.class, tags = "getProjects")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")})
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/")
    private List<ProjectDTO> getAll() {
        List<ProjectDTO> projects = projectFacade.findAll();
        return projects;
    }

    @ApiOperation(value = "Get specific Project in the System ", response = ProjectDTO.class, tags = "getProject")
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    ProjectDTO findById(@PathVariable("id") Integer id) {
        ProjectDTO projectDTO = projectFacade.findById(id);
        return projectDTO;
    }

    @ApiOperation(value = "Update Project in the System ", response = ProjectDTO.class, tags = "updateProject")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('read','write')")
    private ProjectDTO update(@PathVariable("id") Integer id,
                              @RequestBody ProjectDTO projectDTONew)
            throws ResourceNotFoundException {
        ProjectDTO updated = projectFacade.update(id, projectDTONew);
        return updated;
    }

    @ApiOperation(value = "Delete Project by id in the System", response = Integer.class, tags = "deleteProject")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('read','write')")
    private String delete(@PathVariable("id") Integer id) {
        projectFacade.deleteById(id);
        return "Project: " + id + "deleted successfully";
    }
}

















