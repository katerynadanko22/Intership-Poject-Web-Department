package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.ProjectPosition;
import org.example.exception.ResourceNotFoundException;
import org.example.service.ProjectPositionService;
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
    private final ProjectPositionService projectPositionService;

    public ProjectPositionController(ProjectPositionService projectPositionService) {
        this.projectPositionService = projectPositionService;
    }

    @PostMapping(value = "/save/{userId}/{projectId}")
    private ResponseEntity <String> saveProjectPosition(@RequestBody ProjectPosition projectPosition,
                                          @PathVariable("userId") Integer userId,
                                          @PathVariable("projectId") Integer projectId) {
        ProjectPosition savedProjectPosition = projectPositionService.save(projectPosition, userId, projectId);
        return ResponseEntity.ok("ProjectPosition saved successfully: " + savedProjectPosition);
    }

    @GetMapping("/get/{id}")
    ResponseEntity <String>getProjectPositionById(@PathVariable("id") Integer id) {
        ProjectPosition projectPosition = projectPositionService.getById(id);
        return ResponseEntity.ok("ProjectPosition: " + projectPosition);
    }

    @GetMapping("/find/{id}")
    ResponseEntity <String>findProjectPositionById(@PathVariable("id") Integer id) {
        Optional<ProjectPosition> projectPosition = projectPositionService.findById(id);
        return ResponseEntity.ok("ProjectPosition: " + projectPosition.get());
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllProjectPositions() {
        List<ProjectPosition> projectPositions = projectPositionService.findAll();
        return ResponseEntity.ok("ProjectPositions: " + projectPositions);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateProjectPositionById(@RequestBody ProjectPosition projectPositionNew)
            throws ResourceNotFoundException {
        ProjectPosition projectPosition = projectPositionService.findById(projectPositionNew.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ProjectPosition not found for id: " + projectPositionNew.getId()));
        projectPosition.setPositionTitle(projectPosition.getPositionTitle());
        projectPosition.setUser(projectPosition.getUser());
        projectPosition.setProject(projectPosition.getProject());
        projectPosition.setPositionStartDate(projectPosition.getPositionStartDate());
        projectPosition.setPositionEndDate(projectPosition.getPositionEndDate());

        final ProjectPosition updatedProjectPosition = projectPositionService.save(projectPosition);
        return ResponseEntity.ok("ProjectPosition " + updatedProjectPosition + " updated successfully");
    }

    @PostMapping("/")
    private ResponseEntity<String> updateProjectPosition(@RequestBody ProjectPosition projectPositionNew)
            throws ResourceNotFoundException {
        ProjectPosition projectPosition = projectPositionService.findById(projectPositionNew.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ProjectPosition not found for id: " + projectPositionNew.getId()));
        projectPosition.setProject(projectPositionNew.getProject());
        final ProjectPosition updatedProjectPosition = projectPositionService.update(projectPositionNew);
        return ResponseEntity.ok("ProjectPosition " + updatedProjectPosition + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteProjectPositionById(@PathVariable("id") Integer id) {
        try {
            projectPositionService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
