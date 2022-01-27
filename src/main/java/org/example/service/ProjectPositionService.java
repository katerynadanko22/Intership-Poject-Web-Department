package org.example.service;

import org.example.entity.ProjectPosition;


import java.util.List;
import java.util.Optional;

public interface ProjectPositionService {
    ProjectPosition save(ProjectPosition projectPosition,
                         Integer userId, Integer projectId);

    ProjectPosition save(ProjectPosition projectPosition);

    List<ProjectPosition> findAll();

    Optional<ProjectPosition> findById(Integer id);

    ProjectPosition update(ProjectPosition updatedProjectPosition);

    void deleteById(Integer id);

    ProjectPosition getById(Integer id);
}
