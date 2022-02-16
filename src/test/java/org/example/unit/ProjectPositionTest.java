package org.example.unit;

import org.example.entity.ProjectPosition;
import org.example.repository.ProjectPositionRepository;
import org.example.service.impl.ProjectPositionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    public class ProjectPositionTest {

        @InjectMocks
        private ProjectPositionServiceImpl projectPositionService;

        @Mock
        private ProjectPositionRepository projectPositionRepository;

        @Test
        public void whenSaveProjectPosition_shouldReturnProjectPosition() {

            ProjectPosition projectPosition = new ProjectPosition();
            when(projectPositionRepository.save(ArgumentMatchers.any(ProjectPosition.class))).thenReturn(projectPosition);
            ProjectPosition created = projectPositionService.save(projectPosition);
            assertThat(created.getPositionTitle()).isSameAs(projectPosition.getPositionTitle());
            verify(projectPositionRepository).save(projectPosition);
        }

        @Test
        public void shouldReturnAllProjectPositions() {
            List<ProjectPosition> projectPositions = new ArrayList<>();
            projectPositions.add(new ProjectPosition());
            when(projectPositionRepository.findAll()).thenReturn(projectPositions);
            List<ProjectPosition> projectPositions1 = projectPositionService.findAll();
            assertThat(projectPositions.equals(projectPositions1));
        }

        @Test
        public void shouldReturnEmptyList() {
            List<ProjectPosition> projectPositions = new ArrayList<>();
            when(projectPositionRepository.findAll()).thenReturn(projectPositions);
            List<ProjectPosition> projectPositions1 = projectPositionService.findAll();
            assertThat(projectPositions1.isEmpty());
        }

        @Test
        public void whenGivenId_shouldReturnProjectPositionById_ifFound() {
            Integer id = 1;
            ProjectPosition projectPosition = new ProjectPosition();
            projectPosition.setId(id);
            when(projectPositionRepository.findById(id)).thenReturn(Optional.of(projectPosition));
            assertThat(projectPosition.equals(projectPositionRepository.findById(id)));
        }

        @Test
        public void whenGivenId_shouldThrowException_ifProjectPositionDoesntExist() {
            assertThrows(NoSuchElementException.class, () -> projectPositionService.findById(50));
        }

        @Test
        public void whenGivenId_shouldUpdateProjectPosition_ifFound() {
            ProjectPosition projectPosition = new ProjectPosition();
            projectPosition.setId(89);
            projectPosition.setPositionTitle("Test Name");
            ProjectPosition newProjectPosition = new ProjectPosition();
            newProjectPosition.setPositionTitle("Test");

            given(projectPositionRepository.findById(89)).willReturn(Optional.of(projectPosition));
            projectPositionService.update(89, newProjectPosition);

            verify(projectPositionRepository).save(projectPosition);
            assertEquals("Test", projectPosition.getPositionTitle());
        }

        @Test
        public void whenGivenIdForUpdate_shouldThrowException_ifProjectPositionDoesntExist() {
            assertThrows(NoSuchElementException.class, () -> projectPositionService.update(50, new ProjectPosition()));
        }
    }