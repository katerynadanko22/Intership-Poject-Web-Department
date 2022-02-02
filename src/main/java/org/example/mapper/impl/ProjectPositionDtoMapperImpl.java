//package org.example.mapper.impl;
//
//import org.example.dto.ProjectPositionDTO;
//import org.example.entity.ProjectPosition;
//import org.example.mapper.ProjectPositionDtoMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProjectPositionDtoMapperImpl implements ProjectPositionDtoMapper {
//    @Override
//    public ProjectPositionDTO toProjectPositionDto(ProjectPosition projectPosition) {
//        if (projectPosition == null) {
//            return null;
//        }
//        ProjectPositionDTO projectPositionDTO = new ProjectPositionDTO();
//        projectPositionDTO.setProjectDTO(projectPosition.getProject());
//        projectPositionDTO.setUserDTO(projectPosition.getUser());
//        projectPositionDTO.setPositionTitle(projectPosition.getPositionTitle());
//        projectPositionDTO.setPositionStartDate(projectPosition.getPositionStartDate());
//        projectPositionDTO.setPositionEndDate(projectPosition.getPositionEndDate());
//        return projectPositionDTO;
//    }
//
//    @Override
//    public ProjectPosition toProjectPosition(ProjectPositionDTO projectPositionDTO) {
//        if (projectPositionDTO == null) {
//            return null;
//        }
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setProject(projectPositionDTO.getProject());
//        projectPosition.setUser(projectPositionDTO.getUser());
//        projectPosition.setPositionTitle(projectPositionDTO.getPositionTitle());
//        projectPosition.setPositionStartDate(projectPositionDTO.getPositionStartDate());
//        projectPosition.setPositionEndDate(projectPositionDTO.getPositionEndDate());
//        return projectPosition;
//    }
//}
