package org.example.facade;

import org.example.converter.ProjectConverter;
import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.example.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectFacade {
    private final ProjectService projectService;
    private final ProjectConverter projectConverter;

    public ProjectFacade(ProjectService projectService, ProjectConverter projectConverter) {
        this.projectService = projectService;
        this.projectConverter = projectConverter;
    }

    public ProjectDTO save(ProjectDTO projectDTO) {
        Project savedProject = projectService
                .save(projectConverter.fromProjectDTOToProject(projectDTO));
        ProjectDTO savedProjectDTO = projectConverter.fromProjectToProjectDto(savedProject);
        return savedProjectDTO;
    }

    public List<ProjectDTO> findAll() {
        List<Project> projects = projectService.findAll();
        return projects
                .stream()
                .map(projectConverter::fromProjectToProjectDto)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDTO> findById(Integer id) {
        return Optional.ofNullable(projectConverter
                .fromProjectToProjectDto(projectService.findById(id).get()));
    }

    public ProjectDTO getById(Integer id) {
        return projectConverter.fromProjectToProjectDto(projectService.getById(id));
    }

    public ProjectDTO update(ProjectDTO projectDTONew) {
        Project project = projectService.findById(projectDTONew.getId()).get();
        project.setTitle(projectDTONew.getTitle());
        return projectConverter.fromProjectToProjectDto(projectService.save(project));
    }

    public void deleteById(Integer id) {
//        if (departmentService.findById(id).isPresent()) {
        projectService.deleteById(id);
//        }
    }
}
