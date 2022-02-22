package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.service.AvailableEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableEmployeeServiceImpl implements AvailableEmployeeService {
    private final ProjectPositionRepository projectPositionRepository;

    @Override
    public List<ProjectPosition> findAllAvailableNow() {
        return projectPositionRepository.findAllAvailableNow();
    }

    @Override
    public List<ProjectPosition> findAllAvailableNext(int days) {
        return projectPositionRepository.findAllAvailableNext(days);
    }
}
