package org.example.service;

import org.example.entity.ProjectPosition;

import java.util.List;

public interface ProjectPositionService {

    ProjectPosition save(ProjectPosition projectPosition);

    List<ProjectPosition> findAll();

    ProjectPosition findById(Integer id);

    ProjectPosition update(Integer id, ProjectPosition updatedProjectPosition);

    void deleteById(Integer id);

    ProjectPosition updateUserAndProjectInProjectPosition(Integer newProjectId, Integer newUserId, Integer id);

}
