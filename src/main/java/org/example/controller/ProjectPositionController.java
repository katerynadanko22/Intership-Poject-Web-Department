package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;
import org.example.facade.ProjectPositionFacade;
import org.example.facade.UserFacade;
import org.example.modelmapper.ProjectMapper;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.modelmapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/project_positions")
public class ProjectPositionController {
    private final ProjectPositionFacade projectPositionFacade;
    private final UserFacade userFacade;
    private final ProjectFacade projectFacade;
    private final ProjectPositionMapper projectPositionMapper;
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;

    public ProjectPositionController(ProjectPositionFacade projectPositionFacade,
                                     UserFacade userFacade, ProjectFacade projectFacade,
                                     ProjectPositionMapper projectPositionMapper,
                                     UserMapper userMapper, ProjectMapper projectMapper) {
        this.projectPositionFacade = projectPositionFacade;
        this.userFacade = userFacade;
        this.projectFacade = projectFacade;
        this.projectPositionMapper = projectPositionMapper;
        this.userMapper = userMapper;
        this.projectMapper = projectMapper;
    }

    @PostMapping(value = "/save/{userId}/{projectId}")
    private ResponseEntity<String> saveProjectPosition(@RequestBody ProjectPositionDTO projectPosition,
                                                       @PathVariable("userId") Integer userId,
                                                       @PathVariable("projectId") Integer projectId) {
        projectPosition.setUserDTO(userFacade.findById(userId));
        projectPosition.setProjectDTO(projectFacade.findById(projectId));
        ProjectPositionDTO savedProjectPosition = projectPositionFacade.save(projectPosition);
        return ResponseEntity.ok("ProjectPosition saved successfully: " + savedProjectPosition);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<String> getProjectPositionById(@PathVariable("id") Integer id) {
        ProjectPositionDTO projectPosition = projectPositionFacade.getById(id);
        return ResponseEntity.ok("ProjectPosition: " + projectPosition);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<String> findProjectPositionById(@PathVariable("id") Integer id) {
        Optional<ProjectPositionDTO> projectPosition = projectPositionFacade.findById(id);
        return ResponseEntity.ok("ProjectPosition: " + projectPosition.get());
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllProjectPositions() {
        List<ProjectPositionDTO> projectPositions = projectPositionFacade.findAll();
        return ResponseEntity.ok("ProjectPositions: " + projectPositions);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updatePutProjectPositionDTOById(@PathVariable("id") Integer id,
                                                           @RequestBody ProjectPositionDTO dto)
            throws ResourceNotFoundException {
        ProjectPositionDTO updated = projectPositionFacade.update(id, dto);
        return ResponseEntity.ok("ProjectPositions: " + updated);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity <String> deleteProjectPositionById(@PathVariable("id") Integer id) {
        projectPositionFacade.deleteById(id);
        return ResponseEntity.ok("ProjectPositions: " + id + "deleted");
    }
}
