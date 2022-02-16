package org.example.service;

import org.example.entity.ProjectPosition;

import java.util.List;

public interface AvailableEmployeeService {
    List<ProjectPosition> findAllAvailableNow();

    List<ProjectPosition>  findAllAvailableNext(int days);
}
