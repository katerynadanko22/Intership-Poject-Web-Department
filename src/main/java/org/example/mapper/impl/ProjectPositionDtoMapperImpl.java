//package org.example.mapper.impl;
//
//import org.example.dto.ProjectPositionDTO;
//import org.example.entity.ProjectPosition;
//import org.example.mapper.ProjectPositionDtoMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProjectPositionDtoMapperImpl implements ProjectPositionDtoMapper {

//@NotNull
//    @Override
//    public ProjectPositionDTO toProjectPositionDto(ProjectPosition projectPosition) {
//
//        ProjectPositionDTO projectPositionDTO = new ProjectPositionDTO();
//        projectPositionDTO.setProjectDTO(projectPosition.getProject());
//        projectPositionDTO.setUserDTO(projectPosition.getUser());
//        projectPositionDTO.setPositionTitle(projectPosition.getPositionTitle());
//        projectPositionDTO.setPositionStartDate(projectPosition.getPositionStartDate());
//        projectPositionDTO.setPositionEndDate(projectPosition.getPositionEndDate());
//        return projectPositionDTO;
//    }
//@NotNull
//    @Override
//    public ProjectPosition toProjectPosition(ProjectPositionDTO projectPositionDTO) {
//
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setProject(projectPositionDTO.getProject());
//        projectPosition.setUser(projectPositionDTO.getUser());
//        projectPosition.setPositionTitle(projectPositionDTO.getPositionTitle());
//        projectPosition.setPositionStartDate(projectPositionDTO.getPositionStartDate());
//        projectPosition.setPositionEndDate(projectPositionDTO.getPositionEndDate());
//        return projectPosition;
//    }
//}
