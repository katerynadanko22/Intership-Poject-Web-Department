package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProjectDTO;
import org.example.entity.Project;
import org.example.modelmapper.ProjectMapper;
import org.example.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProjectFacade {
    private final ProjectService projectService;
    private final ProjectMapper mapper;

    @Transactional
    public ProjectDTO save(ProjectDTO projectDTO) {
        Project savedProject = projectService.save(mapper.toEntity(projectDTO));
        ProjectDTO savedProjectDTO = mapper.toDto(savedProject);
        return savedProjectDTO;
    }

    public List<ProjectDTO> findAll() {
        List<Project> projects = projectService.findAll();
        return projects
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDTO findById(Integer id) {
        return mapper.toDto(projectService.findById(id));
    }

    @Transactional
    public ProjectDTO update(Integer id, ProjectDTO projectDTONew) {
        Project update = projectService.update(id, mapper.toEntity(projectDTONew));
        return mapper.toDto(update);
    }

    @Transactional
    public void deleteById(Integer id) {
            projectService.deleteById(id);
    }
}

