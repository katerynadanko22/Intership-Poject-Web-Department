//package org.example.unit;
//
//import org.example.entity.Department;
//import org.example.entity.Role;
//import org.example.entity.Status;
//import org.example.entity.User;
//import org.example.exception.DuplicateEntityException;
//import org.example.repository.DepartmentRepository;
//import org.example.repository.UserRepository;
//import org.example.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
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
//public class UserTest {
//
//    @InjectMocks
//    private UserServiceImpl userServiceMock;
//
//    @Mock
//    private UserRepository userRepositoryMock;
//
//    @Mock
//    private DepartmentRepository departmentRepositoryMock;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void whenRegisterUser_shouldReturnUser() {
//        Department department = new Department(1, "Test department");
//        User user = new User(1, "TestName", "Danko", "kateryna@mail.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, department);
//
//        when(userRepositoryMock.save(ArgumentMatchers.any(User.class))).thenReturn(user);
//        User created = userServiceMock.registerUser(user);
//        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
//        verify(userRepositoryMock).save(user);
//    }
//
//    @Test
//    public void whenGivenUser_shouldSave_ButThrowException_ifUserExist() {
//        User user = new User(1, "TestName", "Danko", "kateryna@mail.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(1, "dev"));
//        when(userRepositoryMock.existsUserByEmail(user.getEmail())).thenReturn(true);
//        Assertions.assertThrows(DuplicateEntityException.class, () -> userServiceMock.registerUser(user));
//        verify(userRepositoryMock).existsUserByEmail(user.getEmail());
//    }
//
//    @Test
//    public void shouldReturnAllUsers() {
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        when(userRepositoryMock.findAll()).thenReturn(users);
//        List<User> users2 = userServiceMock.findAll();
//        assertThat(users.equals(users2));
//    }
//
//    @Test
//    public void shouldReturnEmptyList() {
//        List<User> users = new ArrayList<>();
//        when(userRepositoryMock.findAll()).thenReturn(users);
//        List<User> users2 = userServiceMock.findAll();
//        assertThat(users2.isEmpty());
//    }
//
//    @Test
//    public void whenGivenId_shouldReturnUserById_ifFound() {
//        Integer id = 1;
//        User user = new User();
//        user.setId(id);
//        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));
//        assertThat(user.equals(userRepositoryMock.findById(id)));
//    }
//
//    @Test
//    public void whenGivenId_shouldThrowException_ifUserDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.findById(50));
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateUser_ifFound() {
//        User user = new User(10, "Test", "Danko", "kateryna@mali.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(2, "java"));
//        user.setId(89);
//        user.setFirstName("Test Name");
//        User newUser = new User();
//        newUser.setFirstName("Kate");
//        newUser.setLastName("New Test Name");
//        newUser.setEmail("New Test Name");
//        newUser.setPassword("New Test Name");
//        newUser.setJobTitle("New Test Name");
//
//        given(userRepositoryMock.findById(89)).willReturn(Optional.of(user));
//        userServiceMock.update(newUser, 89);
//
//        verify(userRepositoryMock).save(user);
//        assertEquals("Kate", user.getFirstName());
//    }
//    @Test
//    public void whenGivenIdForUpdate_shouldThrowException_ifIdDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.update(new User(), 50));
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateUsersDepartment_ifFound() {
//        User user = new User(10, "Test", "Danko", "kateryna@mali.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(2, "java"));
//        Department department = new Department(15, "dev");
//
//        given(departmentRepositoryMock.findById(15)).willReturn(Optional.of(department));
//        given(userRepositoryMock.findById(10)).willReturn(Optional.of(user));
//        userServiceMock.updateDepartment(15, 10);
//
//        verify(userRepositoryMock).save(user);
//        assertEquals(15, user.getDepartment().getId());
//    }
//
//    @Test
//    public void whenGivenIdForUpdateDepartment_shouldThrowException_ifIdDoesntExist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.updateDepartment(1, 50));
//    }
//    @Test
//    public void whenGivenIdDeleteUser_ifFound() {
//        User user = new User();
//        user.setId(25);
//        userServiceMock.deleteById(25);
//        assertThat(user.equals(null));
//    }
//
//    @Test
//    public void whenGivenIdDelete_ifUserDoesntExist() {
//        userServiceMock.deleteById(25);
//        assertThrows(NoSuchElementException.class,
//                () -> userServiceMock.findById(25));
//    }
//}