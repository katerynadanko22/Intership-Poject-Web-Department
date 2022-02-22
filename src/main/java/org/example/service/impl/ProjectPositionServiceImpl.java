package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.repository.ProjectRepository;
import org.example.repository.UserRepository;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectPositionServiceImpl implements ProjectPositionService {

    private final ProjectPositionRepository projectPositionRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectPosition save(ProjectPosition projectPosition) {
        log.info("projectPosition start to save with id{} ", projectPosition.getId());
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public List<ProjectPosition> findAll() {
        log.info("projectPosition start to get All");
        return projectPositionRepository.findAll();
    }

    @Override
    public ProjectPosition findById(Integer id) {
        log.info("projectPosition start to get by id={} ", id);
        return projectPositionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such project position in BD"));
    }

    @Override
    public ProjectPosition update(Integer id, ProjectPosition positionNew) {
        log.info("projectPosition start to update  by id={} ", id);
        ProjectPosition projectPosition = projectPositionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such project position in BD"));
        projectPosition.setProject(positionNew.getProject());
        projectPosition.setUser(positionNew.getUser());
        projectPosition.setPositionTitle(positionNew.getPositionTitle());
        projectPosition.setPositionStartDate(positionNew.getPositionStartDate());
        projectPosition.setPositionEndDate(positionNew.getPositionEndDate());

        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public ProjectPosition updateUserAndProjectInProjectPosition(Integer newProjectId, Integer newUserId, Integer id) {
        log.info("ProjectPosition start to update User and Project by id {} ", id);
        ProjectPosition projectPosition = projectPositionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No ProjectPosition in BD with id {}" + id));
        projectPosition.setProject(Optional.of(projectRepository.findById(newProjectId)
                .orElseThrow(() -> new NoSuchElementException("No such project in BD with id {}" + newProjectId))).get());
        projectPosition.setUser(Optional.of(userRepository.findById(newUserId)
                .orElseThrow(() -> new NoSuchElementException("No user in BD with id {}" + newUserId))).get());
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("projectPosition start to delete  by id {} ", id);
        if (projectPositionRepository.existsById(id)) {
            projectPositionRepository.deleteById(id);
        }
    }
}





