package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.example.exception.ResourceNotFoundException;
import org.example.facade.ProjectFacade;
import org.example.mapper.ProjectDtoMapper;
import org.example.service.ProjectService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

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
        Optional<ProjectDTO> projectDTO = projectFacade.findById(id);
        return ResponseEntity.ok("Project with id: " + id + " has title: " + projectDTO.get());
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updatePutProjectDTOById(@PathVariable("id") Integer id,
                                                        @RequestBody ProjectDTO projectDTONew)
            throws ResourceNotFoundException {
        ProjectDTO projectDTO = projectFacade.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found for id: " + id));
        projectDTO.setTitle(projectDTONew.getTitle());
        final ProjectDTO updatedProjectDTO = projectFacade.save(projectDTO);
        return ResponseEntity.ok("Project " + updatedProjectDTO + " updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteProjectDTOById(@PathVariable("id") Integer id) {
        try {
            projectFacade.deleteById(id);
            return ResponseEntity.ok("Project with id: " + id + " deleted ");
        } catch (Exception e) {
            return ResponseEntity.ok("BAD_REQUEST for roject with id: " + id);
        }
    }


//
//
//        private ProjectDtoMapper mapper
//            = Mappers.getMapper(ProjectDtoMapper.class);
//    private final ProjectService projectService;
//
//    @Autowired
//    public ProjectController(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//    @PostMapping(value = "/save")
//    private ResponseEntity <String> saveProject(@RequestBody ProjectDTO projectDTO) {
//        Project savedProject = projectService.save(mapper.toProject(projectDTO));
//        return ResponseEntity.ok("Project saved successfully: " + savedProject);
//    }
//
//    @GetMapping("/find/{id}")
//    ResponseEntity<String> findProjectById(@PathVariable("id") Integer id) {
//        Optional<Project> project = Optional.ofNullable(projectService.findById(id).get());
//        return ResponseEntity.ok("Project with id: " + id + " has title: " + project.get());
//    }
//
//    @GetMapping("/get/{id}")
//    ResponseEntity<String> getProjectById(@PathVariable("id") Integer id) {
//        Project project = projectService.getById(id);
//        return ResponseEntity.ok("Project with id: " + id + " has title: " + project);
//    }
//
//
//    @GetMapping(value = "/")
//    private ResponseEntity<String> showAllProjects() {
//        List<Project> projects = projectService.findAll();
//        return ResponseEntity.ok("Projects: " + projects);
//    }
//
//    @PutMapping("/{id}")
//    private ResponseEntity<String> updatePutProjectById(@PathVariable("id") Integer id,
//                                                        @RequestBody ProjectDTO projectDTONew)
//            throws ResourceNotFoundException {
//        Project project = projectService.findById(id).get();
//        project.setTitle(projectDTONew.getTitle());
//        final Project updatedProject = projectService.save(project);
//        return ResponseEntity.ok("Project " + updatedProject + " updated successfully");
//    }
//
//    @PostMapping("/{id}")
//    private ResponseEntity<String> updatePostProject(@PathVariable("id") Integer id,
//                                                     @RequestBody Project projectNew)
//            throws ResourceNotFoundException {
//        Project project = projectService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Project not found for id: " + id));
//        project.setTitle(projectNew.getTitle());
//        final Project updatedProject = projectService.update(projectNew);
//        return ResponseEntity.ok("Project " + updatedProject + " updated successfully");
//    }
//
//    @DeleteMapping("/{id}")
//    private ResponseEntity<String> deleteProjectById(@PathVariable("id") Integer id) {
//        try {
//            projectService.deleteById(id);
//            return  ResponseEntity.ok("Project with id: " + id + " deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.ok("BAD_REQUEST");
//        }
//    }
}
