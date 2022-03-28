//package org.example.unit;
//
//import org.example.entity.Project;
//import org.example.exception.DuplicateEntityException;
//import org.example.repository.ProjectRepository;
//import org.example.service.impl.ProjectServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ProjectTest {
//
//    @InjectMocks
//    private ProjectServiceImpl projectServiceMock;
//
//    @Mock
//    private ProjectRepository projectRepositoryMock;
//
//    @Test
//    public void whenSaveProject_shouldReturnProject() {
//
//        Project project = new Project();
//        when(projectRepositoryMock.save(ArgumentMatchers.any(Project.class))).thenReturn(project);
//        Project created = projectServiceMock.save(project);
//        assertThat(created.getTitle()).isSameAs(project.getTitle());
//        verify(projectRepositoryMock).save(project);
//    }
//
//    @Test
//    public void whenGivenProject_shouldSave_ButThrowException_ifProjectExist() {
//        Project project = new Project();
//        when(projectRepositoryMock.existsByTitle(project.getTitle())).thenReturn(true);
//        Assertions.assertThrows(DuplicateEntityException.class, () -> projectServiceMock.save(project));
//        verify(projectRepositoryMock).existsByTitle(project.getTitle());
//    }
//
//    @Test
//    public void shouldReturnAllProjects() {
//        List<Project> projects = new ArrayList<>();
//        projects.add(new Project());
//        when(projectRepositoryMock.findAll()).thenReturn(projects);
//        List<Project> users2 = projectServiceMock.findAll();
//        assertThat(projects.equals(users2));
//    }
//
//    @Test
//    public void shouldReturnEmptyList() {
//        List<Project> projects = new ArrayList<>();
//        when(projectRepositoryMock.findAll()).thenReturn(projects);
//        List<Project> users2 = projectServiceMock.findAll();
//        assertThat(users2.isEmpty());
//    }
//
//    @Test
//    public void whenGivenId_shouldReturnProjectById_ifFound() {
//        Integer id = 1;
//        Project project = new Project();
//        project.setId(id);
//        when(projectRepositoryMock.findById(id)).thenReturn(Optional.of(project));
//        assertThat(project.equals(projectRepositoryMock.findById(id)));
//    }
//
//    @Test
//    public void whenGivenId_shouldThrowException_ifProjectDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> projectServiceMock.findById(50));
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateProject_ifFound() {
//        Project project = new Project();
//        project.setId(89);
//        project.setTitle("Test Name");
//        Project newProject = new Project();
//        newProject.setTitle("Test");
//
//        given(projectRepositoryMock.findById(89)).willReturn(Optional.of(project));
//        projectServiceMock.update(89, newProject);
//
//        verify(projectRepositoryMock).save(project);
//        assertEquals("Test", project.getTitle());
//    }
//
//    @Test
//    public void whenGivenIdForUpdate_shouldThrowException_ifIProjectDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> projectServiceMock.update(50, new Project()));
//    }
//
//    @Test
//    public void whenGivenIdDeleteProject_ifFound() {
//        Project project = new Project();
//        project.setId(25);
//        projectServiceMock.deleteById(25);
//        assertThat(project.equals(null));
//    }
//
//    @Test
//    public void whenGivenIdDelete_ifProjectDoesntExist() {
//        projectServiceMock.deleteById(25);
//        assertThrows(NoSuchElementException.class,
//                () -> projectServiceMock.findById(25));
//    }
//}
