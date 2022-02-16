package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectPositionFacade {

    private final ProjectPositionService projectPositionService;
    private final ProjectPositionMapper mapper;

    @Transactional
    public ProjectPositionDTO save(ProjectPositionDTO projectPositionDTO) {
        ProjectPosition savedProjectPosition = projectPositionService.save(mapper.toEntity(projectPositionDTO));
        ProjectPositionDTO savedProjectPositionDTO = mapper.toDto(savedProjectPosition);
        return savedProjectPositionDTO;
    }

    public ProjectPositionDTO findById(Integer id) {
        return mapper.toDto(projectPositionService.findById(id));
    }

    public List<ProjectPositionDTO> findAll() {
        List<ProjectPosition> projectPositions = projectPositionService.findAll();
        return projectPositions
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectPositionDTO update(Integer id, ProjectPositionDTO projectPositionDTO) {
        ProjectPosition saveProjectPositionDTOInForm = projectPositionService.update
                (id, mapper.toEntity(projectPositionDTO));
        return mapper.toDto(saveProjectPositionDTOInForm);
    }

    @Transactional
    public void deleteById(Integer id) {
        projectPositionService.deleteById(id);
    }

}

