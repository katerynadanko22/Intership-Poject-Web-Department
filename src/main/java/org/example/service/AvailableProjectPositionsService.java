package org.example.service;

import org.example.entity.ProjectPosition;

import java.util.List;

public interface AvailableProjectPositionsService {
    List<ProjectPosition> findAllAvailableProjectPositionsNow();

    List<ProjectPosition> findAllAvailableProjectPositionsNextDays(int days);
}
