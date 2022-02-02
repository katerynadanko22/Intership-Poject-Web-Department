package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;

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
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectFacade projectFacade;

    @PostMapping(value = "/")
    private ProjectDTO save(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProjectDTO = projectFacade.save(projectDTO);
        return savedProjectDTO;
    }

    @GetMapping(value = "/")
    private List<ProjectDTO> getAll() {
        List<ProjectDTO> projects = projectFacade.findAll();
        return projects;
    }

    @GetMapping("/{id}")
    ProjectDTO findById(@PathVariable("id") Integer id) {
        ProjectDTO projectDTO = projectFacade.findById(id);
        return projectDTO;
    }

    @PutMapping("/{id}")
    private ProjectDTO update(@PathVariable("id") Integer id,
                                                           @RequestBody ProjectDTO projectDTONew)
            throws ResourceNotFoundException {
        ProjectDTO updated = projectFacade.update(id, projectDTONew);
        return updated;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        projectFacade.deleteById(id);
        return "Project: " + id + "deleted successfully";
    }
}
