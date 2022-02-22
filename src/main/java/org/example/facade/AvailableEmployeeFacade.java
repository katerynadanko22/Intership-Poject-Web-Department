package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.service.AvailableEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableEmployeeFacade {
    private final AvailableEmployeeService availableEmployeeService;
    private final ProjectPositionMapper mapper;

    @Transactional
    public List<ProjectPositionDTO> findAllAvailableProjectPositionsNow() {
        List<ProjectPosition> allAvailableProjectPositionsNow = availableEmployeeService.findAllAvailableNow();
        return allAvailableProjectPositionsNow
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProjectPositionDTO> findAllAvailableProjectPositionsNext(int days) {
        List<ProjectPosition> allAvailableProjectPositionsNext = availableEmployeeService.findAllAvailableNext(days);
        return allAvailableProjectPositionsNext
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
