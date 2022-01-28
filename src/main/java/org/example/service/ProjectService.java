package org.example.service;

import org.example.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project save(Project project);

    List<Project> findAll();

    Optional<Project> findById(Integer id);

    Project update(Integer id, Project updatedProject);

    void deleteById(Integer id);

    Project getById(Integer id);
}


