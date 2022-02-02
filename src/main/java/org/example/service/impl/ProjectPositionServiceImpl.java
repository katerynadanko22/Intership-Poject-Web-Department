package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectPositionServiceImpl implements ProjectPositionService {

    private final ProjectPositionRepository projectPositionRepository;

    @Override
    public ProjectPosition save(ProjectPosition projectPosition) {
        log.info(String.format("projectPosition.save {id = %d, project = %s}",
                projectPosition.getId(), projectPosition.getProject()));
        return projectPositionRepository.save(projectPosition);
    }

    @Override
    public List<ProjectPosition> findAll() {
        log.info("projectPosition.getAll: " + projectPositionRepository.findAll());
        return projectPositionRepository.findAll();
    }

    @Override
    public ProjectPosition findById(Integer id) {
        return projectPositionRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No such project position in BD"));
    }

    @Override
    public ProjectPosition update(Integer id, ProjectPosition updatedProjectPosition) {

        ProjectPosition projectPosition = projectPositionRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("No such project position in BD"));
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