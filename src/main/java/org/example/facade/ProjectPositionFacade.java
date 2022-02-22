package org.example.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.entity.Project;
import org.example.entity.ProjectPosition;
import org.example.entity.User;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.repository.ProjectRepository;
import org.example.repository.UserRepository;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectPositionFacade {

    private final ProjectPositionService projectPositionService;
    private final ProjectPositionMapper mapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProjectPositionDTO save(ProjectPositionDTO projectPositionDTO) {
        ProjectPosition projectPosition = mapper.toEntity(projectPositionDTO);

        Integer userId = projectPositionDTO.getUser().getId();
        User user = Optional.ofNullable(userRepository.getOne(userId)).orElseThrow(() -> new NoSuchElementException("No User in DB with id {}" + userId));
        projectPosition.setUser(user);

        Integer projectId = projectPositionDTO.getProject().getId();
        Project project = Optional.ofNullable(projectRepository.getOne(projectId)).orElseThrow(()->new NoSuchElementException("No Project in DB with id {}" + projectId));
        projectPosition.setProject(project);


        ProjectPosition savedProjectPosition = projectPositionService.save(projectPosition);
        return mapper.toDto(savedProjectPosition);
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

    public ProjectPositionDTO updateUserAndProjectInProjectPosition(Integer newProjectId, Integer newUserId, Integer id) {
        ProjectPosition updated = projectPositionService.updateUserAndProjectInProjectPosition(newProjectId, newUserId, id);
        return mapper.toDto(updated);
    }
    @Transactional
    public void deleteById(Integer id) {
        projectPositionService.deleteById(id);
    }
}

