package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.repository.ProjectRepository;
import org.example.repository.UserRepository;
import org.example.service.ProjectPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ProjectPositionServiceImpl implements ProjectPositionService {

    private final ProjectPositionRepository projectPositionRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectPositionServiceImpl(ProjectPositionRepository projectPositionRepository,
                                      UserRepository userRepository, ProjectRepository projectRepository) {
        this.projectPositionRepository = projectPositionRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectPosition save(ProjectPosition projectPosition) {
        log.info(String.format("projectPosition.save {id = %d, project = %s}",
                projectPosition.getId(), projectPosition.getProject()));
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public ProjectPosition getById(Integer id) {
        if (!projectPositionRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return projectPositionRepository.getById(id);
    }

    @Override
    public List<ProjectPosition> findAll() {
        log.info("projectPosition.getAll: " + projectPositionRepository.findAll());
        return projectPositionRepository.findAll();
    }

    @Override
    public Optional<ProjectPosition> findById(Integer id) {
        if (!projectPositionRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        return projectPositionRepository.findById(id);
    }

    @Override
    public ProjectPosition update(Integer id, ProjectPosition updatedProjectPosition) {
        if (!projectPositionRepository.findById(id).isPresent()) {
            throw new NoSuchElementException();
        }
        ProjectPosition projectPosition = projectPositionRepository.findById(id).get();
        projectPosition.setProject(updatedProjectPosition.getProject());
        projectPosition.setUser(updatedProjectPosition.getUser());
        projectPosition.setPositionTitle(updatedProjectPosition.getPositionTitle());
        projectPosition.setPositionStartDate(updatedProjectPosition.getPositionStartDate());
        projectPosition.setPositionEndDate(updatedProjectPosition.getPositionEndDate());

        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public void deleteById(Integer id) {
        if (projectPositionRepository.findById(id).isPresent()) {
        projectPositionRepository.deleteById(id);
        }
    }
}