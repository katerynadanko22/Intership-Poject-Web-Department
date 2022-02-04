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
        log.info("projectPosition start to get by id {} ", id);
        return projectPositionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such project position in BD"));
    }

    @Override
    public ProjectPosition update(Integer id, ProjectPosition positionNew) {
        log.info("projectPosition start to update  by id {} ", id);
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
    public void deleteById(Integer id) {
        log.info("projectPosition start to delete  by id {} ", id);
        if (projectPositionRepository.existsById(id)) {
            projectPositionRepository.deleteById(id);
        }
    }
}





