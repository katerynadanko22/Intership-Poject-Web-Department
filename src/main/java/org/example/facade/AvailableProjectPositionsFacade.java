package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProjectPositionDTO;
import org.example.entity.ProjectPosition;
import org.example.modelmapper.ProjectPositionMapper;
import org.example.service.AvailableProjectPositionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableProjectPositionsFacade {
    private final AvailableProjectPositionsService availableProjectPositionsService;
    private final ProjectPositionMapper mapper;

    @Transactional
    public List<ProjectPositionDTO> findAllAvailableProjectPositionsNow() {
        List<ProjectPosition> allAvailableProjectPositionsNow = availableProjectPositionsService.findAllAvailableProjectPositionsNow();
        return allAvailableProjectPositionsNow
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProjectPositionDTO> findAllAvailableProjectPositionsNext(int days) {
        List<ProjectPosition> allAvailableProjectPositionsNext = availableProjectPositionsService.findAllAvailableProjectPositionsNextDays(days);
        return allAvailableProjectPositionsNext
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
