package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Project;
import org.example.repository.ProjectRepository;
import org.example.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

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
    public Project findById(Integer id) {
        return projectRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No such project in BD"));
    }

    @Override
    public Project update(Integer id, Project updatedProject) {

        Project project = projectRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No such project in BD"));
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
