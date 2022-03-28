package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.service.AvailableProjectPositionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableProjectPositionsServiceImpl implements AvailableProjectPositionsService {
    private final ProjectPositionRepository projectPositionRepository;

    @Override
    public List<ProjectPosition> findAllAvailableProjectPositionsNow() {
        return projectPositionRepository.findAllAvailableProjectPositionsNow();
    }

    @Override
    public List<ProjectPosition> findAllAvailableProjectPositionsNextDays(int days) {
        return projectPositionRepository.findAllAvailableProjectPositionsNextDays(days);
    }
}
