package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;
import org.example.facade.ProjectPositionFacade;
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

@Api(value = "Swagger2DemoRestController", description = "REST Apis related to ProjectPosition Entity")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/project-positions")
public class ProjectPositionController {
    private final ProjectPositionFacade projectPositionFacade;
    private final UserFacade userFacade;
    private final ProjectFacade projectFacade;

    @ApiOperation(value = "Save new ProjectPosition in the System ", response = ProjectPositionDTO.class, tags = "saveProjectPosition")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @PostMapping(value = "/{userId}/{projectId}")
    private ProjectPositionDTO save(@RequestBody ProjectPositionDTO projectPosition,
                                    @PathVariable("userId") Integer userId,
                                    @PathVariable("projectId") Integer projectId) {
        projectPosition.setUser(userFacade.findById(userId));
        projectPosition.setProject(projectFacade.findById(projectId));
        return projectPositionFacade.save(projectPosition);
    }

    @ApiOperation(value = "Get specific ProjectPosition in the System ", response = ProjectPositionDTO.class, tags = "geProjectPosition")
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    ProjectPositionDTO findById(@PathVariable("id") Integer id) {
        return projectPositionFacade.findById(id);
    }

    @ApiOperation(value = "Get list of ProjectPositions in the System ", response = Iterable.class, tags = "getProjectPositions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/")
    private List<ProjectPositionDTO> getAll() {
        List<ProjectPositionDTO> projectPositions = projectPositionFacade.findAll();
        return projectPositions;
    }

    @ApiOperation(value = "Update ProjectPosition in the System ", response = ProjectPositionDTO.class, tags = "updateProjectPosition")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @PutMapping("/{id}")
    private ProjectPositionDTO update(@PathVariable("id") Integer id,
                                      @RequestBody ProjectPositionDTO dto)
            throws ResourceNotFoundException {
        ProjectPositionDTO updated = projectPositionFacade.update(id, dto);
        return updated;
    }

    @ApiOperation(value = "Delete ProjectPosition by id in the System", response = Integer.class, tags = "deleteProjectPosition")
    @PreAuthorize("hasAnyAuthority('read','write')")
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        projectPositionFacade.deleteById(id);
        return "ProjectPositions: " + id + "deleted successfully";
    }

    @ApiOperation(value = "Get list of ProjectPositions Available Now in the System ", response = Iterable.class, tags = "findAllAvailableNow")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/available-now")
    private List<ProjectPositionDTO> findAllAvailableNow() {
        return projectPositionFacade.findAllAvailableNow();
    }

    @ApiOperation(value = "Get list of ProjectPositions Available Next in the System ", response = Iterable.class, tags = "findAllAvailableNext")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/available-next/{days}")
    private List<ProjectPositionDTO> findAllAvailableNext(@PathVariable("days")  int days) {
        return projectPositionFacade.findAllAvailableNext(days);
    }
}











