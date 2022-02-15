package org.example.unit;

import org.example.entity.Project;
import org.example.exception.DuplicateEntityException;
import org.example.repository.ProjectRepository;
import org.example.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Assertions;
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
public class ProjectTest {

        @InjectMocks
        private ProjectServiceImpl projectService;

        @Mock
        private ProjectRepository projectRepository;

        @Test
        public void whenSaveProject_shouldReturnProject() {

            Project project = new Project();
            when(projectRepository.save(ArgumentMatchers.any(Project.class))).thenReturn(project);
            Project created = projectService.save(project);
            assertThat(created.getTitle()).isSameAs(project.getTitle());
            verify(projectRepository).save(project);
        }

        @Test
        public void whenGivenProject_shouldSave_ButThrowException_ifProjectExist() {
            Project project = new Project();
            when(projectRepository.existsByTitle(project.getTitle())).thenReturn(true);
            Assertions.assertThrows(DuplicateEntityException.class, () -> projectService.save(project));
            verify(projectRepository).existsByTitle(project.getTitle());
        }

        @Test
        public void shouldReturnAllProjects() {
            List<Project> projects = new ArrayList<>();
            projects.add(new Project());
            when(projectRepository.findAll()).thenReturn(projects);
            List<Project> users2 = projectService.findAll();
            assertThat(projects.equals(users2));
        }

        @Test
        public void shouldReturnEmptyList() {
            List<Project> projects = new ArrayList<>();
            when(projectRepository.findAll()).thenReturn(projects);
            List<Project> users2 = projectService.findAll();
            assertThat(users2.isEmpty());
        }

        @Test
        public void whenGivenId_shouldReturnProjectById_ifFound() {
            Integer id = 1;
            Project project = new Project();
            project.setId(id);
            when(projectRepository.findById(id)).thenReturn(Optional.of(project));
            assertThat(project.equals(projectRepository.findById(id)));
        }

        @Test
        public void whenGivenId_shouldThrowException_ifProjectDoesntExist() {
            assertThrows(NoSuchElementException.class, () -> projectService.findById(50));
        }

        @Test
        public void whenGivenId_shouldUpdateProject_ifFound() {
            Project project = new Project();
            project.setId(89);
            project.setTitle("Test Name");
            Project newProject = new Project();
            newProject.setTitle("Test");

            given(projectRepository.findById(89)).willReturn(Optional.of(project));
            projectService.update(89, newProject);

            verify(projectRepository).save(project);
            assertEquals("Test", project.getTitle());
        }

        @Test
        public void whenGivenIdForUpdate_shouldThrowException_ifIProjectDoesntExist() {
            assertThrows(NoSuchElementException.class, () -> projectService.update(50, new Project()));
        }

    }
