package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Project;
import org.example.exception.DuplicateEntityException;
import org.example.repository.ProjectRepository;
import org.example.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        log.info("project start to save with id{} ", project.getId());
        log.error("project already exist with id{} ", project.getId());
        if (projectRepository.existsByTitle(project.getTitle())) {
            throw new DuplicateEntityException("Project with title {} already exist" + project.getId());
        }
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        log.info("project start to get All");
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Integer id) {
        log.info("project start to get by id {} ", id);
        return projectRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such project in BD"));
    }

    @Override
    public Project update(Integer id, Project projectNew) {
        log.info("project start to update  by id {} ", id);
        Project project = projectRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such project in BD"));
        project.setTitle(projectNew.getTitle());
        project.setStartDate(projectNew.getStartDate());
        project.setEndDate(projectNew.getEndDate());

        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("project start to delete  by id {} ", id);
        if (projectRepository.existsById(id)) {
            // existsById(Integer id) returns boolean where as findById(Integer id) returns Optional(Object).
            projectRepository.deleteById(id);
        }
    }

}



