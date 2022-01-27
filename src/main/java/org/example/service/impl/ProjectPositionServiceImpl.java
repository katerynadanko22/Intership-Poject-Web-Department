package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Project;
import org.example.entity.ProjectPosition;
import org.example.entity.User;
import org.example.repository.ProjectPositionRepository;
import org.example.repository.ProjectRepository;
import org.example.repository.UserRepository;
import org.example.service.ProjectPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
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

    @Valid
    @Override
    public ProjectPosition save(ProjectPosition projectPosition,
                                Integer userId, Integer projectId) {
        log.info(String.format("projectPosition.save {id = %d, project = %s}",
                projectPosition.getId(), projectPosition.getProject()));
        if (userId == null) {
            throw new RuntimeException("Required parameters: userId");
        }
//        for (User u : userRepository.findAll()) {
//            projectPosition.setUser(u);
//        }
        projectPosition.setUser(userRepository.getById(userId));

        if (projectId == null) {
            throw new RuntimeException("Required parameters: projectId");
        }
//        for (Project p : projectRepository.findAll()) {
//            projectPosition.setProject(p);
//        }
        projectPosition.setProject(projectRepository.getById(projectId));
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public ProjectPosition save(ProjectPosition projectPosition) {
        log.info(String.format("projectPosition.save {id = %d, project = %s}",
                projectPosition.getId(), projectPosition.getProject()));
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public ProjectPosition getById(Integer id) {
        return projectPositionRepository.getById(id);
    }

    @Override
    public List<ProjectPosition> findAll() {
        log.info("projectPosition.getAll: " + projectPositionRepository.findAll());
        return projectPositionRepository.findAll();
    }

    @Override
    public Optional<ProjectPosition> findById(Integer id) {
        return projectPositionRepository.findById(id);
    }

    @Override
    public ProjectPosition update(ProjectPosition updatedProjectPosition) {
        ProjectPosition projectPosition = projectPositionRepository.getById(updatedProjectPosition.getId());
        projectPosition.setProject(updatedProjectPosition.getProject());

        return projectPosition;
    }

    @Override
    public void deleteById(Integer id) {
        if (projectPositionRepository.findById(id).isPresent()) {
            projectPositionRepository.deleteById(id);
        }
    }
}