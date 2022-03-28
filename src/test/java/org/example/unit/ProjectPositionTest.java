//package org.example.unit;
//
//import org.example.entity.Department;
//import org.example.entity.Project;
//import org.example.entity.ProjectPosition;
//import org.example.entity.Role;
//import org.example.entity.Status;
//import org.example.entity.User;
//import org.example.repository.ProjectPositionRepository;
//import org.example.repository.ProjectRepository;
//import org.example.repository.UserRepository;
//import org.example.service.impl.ProjectPositionServiceImpl;
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
//public class ProjectPositionTest {
//
//    @InjectMocks
//    private ProjectPositionServiceImpl projectPositionServiceMock;
//
//    @Mock
//    private ProjectPositionRepository projectPositionRepositoryMock;
//
//    @Mock
//    private ProjectRepository projectRepositoryMock;
//
//    @Mock
//    private UserRepository userRepositoryMock;
//
//    @Test
//    public void whenSaveProjectPosition_shouldReturnProjectPosition() {
//
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setPositionTitle("Best Project");
//        when(projectPositionRepositoryMock.save(ArgumentMatchers.any(ProjectPosition.class))).thenReturn(projectPosition);
//        ProjectPosition created = projectPositionServiceMock.save(projectPosition);
//
//        assertThat(created.getPositionTitle()).isSameAs(projectPosition.getPositionTitle());
//        verify(projectPositionRepositoryMock).save(projectPosition);
//    }
//
//    @Test
//    public void shouldReturnAllProjectPositions() {
//        List<ProjectPosition> projectPositions = new ArrayList<>();
//        projectPositions.add(new ProjectPosition());
//        when(projectPositionRepositoryMock.findAll()).thenReturn(projectPositions);
//        List<ProjectPosition> projectPositions1 = projectPositionServiceMock.findAll();
//        assertThat(projectPositions.equals(projectPositions1));
//    }
//
//    @Test
//    public void shouldReturnEmptyList() {
//        List<ProjectPosition> projectPositions = new ArrayList<>();
//        when(projectPositionRepositoryMock.findAll()).thenReturn(projectPositions);
//        List<ProjectPosition> projectPositions1 = projectPositionServiceMock.findAll();
//        assertThat(projectPositions1.isEmpty());
//    }
//
//    @Test
//    public void whenGivenId_shouldReturnProjectPositionById_ifFound() {
//        Integer id = 1;
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setId(id);
//        when(projectPositionRepositoryMock.findById(id)).thenReturn(Optional.of(projectPosition));
//        assertThat(projectPosition.equals(projectPositionRepositoryMock.findById(id)));
//    }
//
//    @Test
//    public void whenGivenId_shouldThrowException_ifProjectPositionDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> projectPositionServiceMock.findById(50));
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateProjectPosition_ifFound() {
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setId(89);
//        projectPosition.setPositionTitle("Test Name");
//        ProjectPosition newProjectPosition = new ProjectPosition();
//        newProjectPosition.setPositionTitle("Test");
//
//        given(projectPositionRepositoryMock.findById(89)).willReturn(Optional.of(projectPosition));
//        projectPositionServiceMock.update(89, newProjectPosition);
//
//        verify(projectPositionRepositoryMock).save(projectPosition);
//        assertEquals("Test", projectPosition.getPositionTitle());
//    }
//
//    @Test
//    public void whenGivenIdForUpdate_shouldThrowException_ifProjectPositionDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> projectPositionServiceMock.update(50, new ProjectPosition()));
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateUserAndProjectInProjectPosition_ifFound() {
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setId(25);
//        User user = new User(5, "Kate", "Danko", "kateryna@mali.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(2, "java"));
//        Project project = new Project();
//        project.setId(3);
//        given(projectRepositoryMock.findById(3)).willReturn(Optional.of(project));
//        given(userRepositoryMock.findById(5)).willReturn(Optional.of(user));
//        given(projectPositionRepositoryMock.findById(25)).willReturn(Optional.of(projectPosition));
//        projectPositionServiceMock.updateUserAndProjectInProjectPosition(3, 5,25);
//
//        verify(projectPositionRepositoryMock).save(projectPosition);
//        assertEquals(3, projectPosition.getProject().getId());
//    }
//
//    @Test
//    public void whenGivenIdForUpdateUserAndProjectInProjectPosition_shouldThrowException_ifIdDoesntExist() {
//        assertThrows(NoSuchElementException.class,
//                () -> projectPositionServiceMock.updateUserAndProjectInProjectPosition(1, 50,20));
//    }
//
//    @Test
//    public void whenGivenIdDeleteProjectPosition_ifFound() {
//        ProjectPosition projectPosition = new ProjectPosition();
//        projectPosition.setId(25);
//        projectPositionServiceMock.deleteById(25);
//        assertThat(projectPosition.equals(null));
//    }
//
//    @Test
//    public void whenGivenIdDelete_ifProjectPositionDoesntExist() {
//        projectPositionServiceMock.deleteById(14);
//        assertThrows(NoSuchElementException.class,
//                () -> projectPositionServiceMock.findById(14));
//    }
//}