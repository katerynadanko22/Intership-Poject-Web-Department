package org.example.unit;

import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.service.AvailableEmployeeService;
import org.example.service.impl.AvailableEmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvailableEmployeesTest {

    @InjectMocks
    private AvailableEmployeeServiceImpl availableEmployeeService;

    @Mock
    private ProjectPositionRepository projectPositionRepository;

    @Test
    public void shouldReturnAvailableNowEmployees_ifFound() {
        List<ProjectPosition> projectPositions = new ArrayList<>();
        projectPositions.add(new ProjectPosition());
        when(projectPositionRepository.findAllAvailableNow()).thenReturn(projectPositions);
        List<ProjectPosition> projectPositions1 = availableEmployeeService.findAllAvailableNow();
        assertThat(projectPositions.equals(projectPositions1));
    }

    @Test
    public void shouldReturnEmptyList_AvailableNowEmployees_ifDoesntExist() {
        List<ProjectPosition> projectPositions = new ArrayList<>();
        when(projectPositionRepository.findAllAvailableNow()).thenReturn(projectPositions);
        List<ProjectPosition> projectPositions1 = availableEmployeeService.findAllAvailableNow();
        assertThat(projectPositions1.isEmpty());
    }

    @Test
    public void shouldReturnAvailableNextEmployees_ifFound() {
        List<ProjectPosition> projectPositions = new ArrayList<>();
        projectPositions.add(new ProjectPosition());
        when(projectPositionRepository.findAllAvailableNext(5)).thenReturn(projectPositions);
        List<ProjectPosition> projectPositions1 = availableEmployeeService.findAllAvailableNext(5);
        assertThat(projectPositions.equals(projectPositions1));
    }

    @Test
    public void shouldReturnEmptyList_AvailableNextEmployees_ifDoesntExist() {
        List<ProjectPosition> projectPositions = new ArrayList<>();
        when(projectPositionRepository.findAllAvailableNext(5)).thenReturn(projectPositions);
        List<ProjectPosition> projectPositions1 = availableEmployeeService.findAllAvailableNext(5);
        assertThat(projectPositions1.isEmpty());
    }
}
