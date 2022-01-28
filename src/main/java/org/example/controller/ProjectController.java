package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;

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
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectFacade projectFacade;

    public ProjectController(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @PostMapping(value = "/save")
    private ResponseEntity<String> saveProjectDTO(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProjectDTO = projectFacade.save(projectDTO);
        return ResponseEntity.ok("Project saved successfully: " + savedProjectDTO);
    }

    @GetMapping(value = "/")
    private ResponseEntity<String> showAllProjectsDTO() {
        List<ProjectDTO> projects = projectFacade.findAll();
        return ResponseEntity.ok("Projects: " + projects);
    }

    @Transactional
    @GetMapping("/get/{id}")
    ResponseEntity<String> getProjectDTOById(@PathVariable("id") Integer id) {
        ProjectDTO projectDTO = projectFacade.getById(id);
        return ResponseEntity.ok("Project with id: " + id + " has title: " + projectDTO);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<String> findProjectDTOById(@PathVariable("id") Integer id) {
        ProjectDTO projectDTO = projectFacade.findById(id);
        return ResponseEntity.ok("Project with id: " + id + " has title: " + projectDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updatePutProjectDTOById(@PathVariable("id") Integer id,
                                                           @RequestBody ProjectDTO projectDTONew)
            throws ResourceNotFoundException {
        ProjectDTO updated = projectFacade.update(id, projectDTONew);
        return ResponseEntity.ok("Project " + updated + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteProjectDTOById(@PathVariable("id") Integer id) {
        projectFacade.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
