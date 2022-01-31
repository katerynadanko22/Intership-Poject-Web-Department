package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Project;
import org.example.repository.ProjectRepository;
import org.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Valid
    @Override
    public Project save(Project project) {
        log.info(String.format("project.save {id = %d, title = %s}", project.getId(), project.getTitle()));
        Project savedProject = projectRepository.save(project);
        return savedProject;
    }

    @Override
    public List<Project> findAll() {
        log.info("project.getAll: " + projectRepository.findAll());
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Integer id) {
        if (!projectRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return projectRepository.findById(id);
    }

    @Override
    public Project getById(Integer id) {
        if (!projectRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return projectRepository.getById(id);
    }

    @Override
    public Project update(Integer id, Project updatedProject) {
        if (!projectRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        Project project = projectRepository.findById(id).get();
        project.setTitle(updatedProject.getTitle());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());

        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Integer id) {
        if (projectRepository.findById(id).isPresent()) {
        projectRepository.deleteById(id);
        }
    }

}
