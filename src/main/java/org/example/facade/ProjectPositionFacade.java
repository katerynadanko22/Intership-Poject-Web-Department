package org.example.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectDTO;
import org.example.dto.ProjectPositionDTO;
import org.example.entity.Project;
import org.example.entity.ProjectPosition;;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectPositionFacade {

    private final ProjectPositionService projectPositionService;
    private final ProjectPositionMapper mapper;

    public ProjectPositionFacade(ProjectPositionService projectPositionService,
                                 ProjectPositionMapper mapper) {
        this.projectPositionService = projectPositionService;
        this.mapper = mapper;
    }


    public ProjectPositionDTO save(ProjectPositionDTO dto) {
//        log.info(String.format("ProjectPosition.save {id = %d, project = %s}",
//                projectPositionDTO.getId(), projectPositionDTO.getProject()));
//        ProjectPosition savedProjectPositionDTO = projectPositionService.save
//                (projectPositionConverter.fromProjectPositionDtoToProjectPosition
//                        (projectPositionDTO), userId, projectId);
//        log.info(String.format("ProjectPosition.save {id = %d, project = %s}",
//                savedProjectPositionDTO.getId(), savedProjectPositionDTO.getProject()));

        ProjectPosition projectPosition = mapper.toEntity(dto);
        return mapper.toDto(projectPosition);

    }

//    public ProjectPositionDTO save(ProjectPositionDTO projectPositionDTO) {
//        log.info(String.format("ProjectPosition.save {id = %d, project = %s}",
//                projectPositionDTO.getId(), projectPositionDTO.getProject()));
//        ProjectPosition savedProjectPositionDTO = projectPositionService.save
//                (projectPositionConverter.fromProjectPositionDtoToProjectPosition
//                        (projectPositionDTO));
//        log.info(String.format("user.save {id = %d, project = %s}",
//                savedProjectPositionDTO.getId(), savedProjectPositionDTO.getProject()));
//        return projectPositionConverter.fromProjectPositionToProjectPositionDto
//                (savedProjectPositionDTO);
//    }

    public Optional<ProjectPositionDTO> findById(Integer id) {
        return Optional.ofNullable(mapper.toDto(projectPositionService
                .findById(id).get()));
    }

    public List<ProjectPositionDTO> findAll() {
        List<ProjectPosition> projectPositions = projectPositionService.findAll();
        return projectPositions
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ProjectPositionDTO getById(Integer id) {
        return mapper.toDto(
                (projectPositionService.getById(id)));
    }

    public ProjectPositionDTO update(Integer id, ProjectPositionDTO projectPositionDTO) {
        ProjectPosition saveProjectPositionDTOInForm = projectPositionService.update
                (id, mapper.toEntity(projectPositionDTO));
        return mapper.toDto(saveProjectPositionDTOInForm);
    }

    public void deleteById(Integer id) {
        projectPositionService.deleteById(id);
//        }
    }
}

