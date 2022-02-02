package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;
import org.example.facade.ProjectPositionFacade;
import org.example.facade.UserFacade;
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

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/project-positions")
public class ProjectPositionController {
    private final ProjectPositionFacade projectPositionFacade;
    private final UserFacade userFacade;
    private final ProjectFacade projectFacade;


    @PostMapping(value = "/{userId}/{projectId}")
    private ProjectPositionDTO save(@RequestBody ProjectPositionDTO projectPosition,
                                    @PathVariable("userId") Integer userId,
                                    @PathVariable("projectId") Integer projectId) {
        projectPosition.setUser(userFacade.findById(userId));
        projectPosition.setProject(projectFacade.findById(projectId));
        return projectPositionFacade.save(projectPosition);
    }

    @GetMapping("/{id}")
    ProjectPositionDTO findById(@PathVariable("id") Integer id) {
        ProjectPositionDTO projectPosition = projectPositionFacade.findById(id);
        return projectPosition;
    }

    @GetMapping(value = "/")
    private List<ProjectPositionDTO> getAll() {
        List<ProjectPositionDTO> projectPositions = projectPositionFacade.findAll();
        return projectPositions;
    }

    @PutMapping("/{id}")
    private ProjectPositionDTO update(@PathVariable("id") Integer id,
                                      @RequestBody ProjectPositionDTO dto)
            throws ResourceNotFoundException {
        ProjectPositionDTO updated = projectPositionFacade.update(id, dto);
        return updated;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        projectPositionFacade.deleteById(id);
        return "ProjectPositions: " + id + "deleted successfully";
    }
}
