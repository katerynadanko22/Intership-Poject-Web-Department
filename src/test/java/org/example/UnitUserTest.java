//package org.example;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.example.entity.Department;
//import org.example.entity.Role;
//import org.example.entity.Status;
//import org.example.entity.User;
//import org.example.repository.UserRepository;
//import org.example.service.impl.UserServiceImpl;
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
//@ExtendWith(MockitoExtension.class)
//public class UnitUserTest {
//
//    @Mock
//    private UserRepository userRepositoryMock;
//
//    @InjectMocks
//    private UserServiceImpl userServiceMock;
//
//    @Test
//    public void testSaveUser() {
//        User userKate = new User(10, "Kate", "Danko", "kateryna@mali.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(1, "dev"));
//        when(userRepositoryMock.save(userKate)).thenReturn(userKate);
//        User savedUser = userServiceMock.save(userKate);
//        assertEquals("Kate", savedUser.getFirstName());
//        assertThat(savedUser.getId()).isGreaterThan(0);
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
//    public void shouldReturnUserById() {
//        Integer id = 1;
//        User user = new User();
//        user.setId(id);
//        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));
//        assertThat(user.equals(userRepositoryMock.findById(id)));
//    }
//
//    @Test
//    public void get_should_throw_exception_when_id_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.findById(50));
//    }
//
//
//    @Test
//    public void whenSaveUser_shouldReturnUser() {
//        User user = new User();
//        user.setFirstName("TestName");
//        when(userRepositoryMock.save(ArgumentMatchers.any(User.class))).thenReturn(user);
//        User created = userServiceMock.save(user);
//        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
//        verify(userRepositoryMock).save(user);
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateUser_ifFound() {
//        User user = new User();
//        user.setId(89);
//        user.setFirstName("Test Name");
//
//        User newUser = new User();
//        newUser.setFirstName("Kate");
//        newUser.setLastName("New Test Name");
//        newUser.setEmail("New Test Name");
//        newUser.setPassword("New Test Name");
//        newUser.setJobTitle("New Test Name");
//        newUser.setDepartment(new Department(1, "dev"));
//
//        given(userRepositoryMock.findById(89)).willReturn(Optional.of(user));
//        userServiceMock.update(89, newUser);
//
//        verify(userRepositoryMock).save(user);
//        assertEquals("Kate", user.getFirstName());
//    }
//
//    @Test
//    public void should_throw_exception_when_update_user_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.update(50, new User()));
//    }
//
//    @Test
//    public void whenGivenId_shouldDeleteUser_ifFound() {
//        User user = new User();
//        user.setLastName("TestName");
//        user.setId(1);
//        lenient().when(userRepositoryMock.findById(user.getId())).thenReturn(java.util.Optional.of(user));
//        userServiceMock.deleteById(user.getId());
//        verify(userRepositoryMock).deleteById(user.getId());
//    }
//
//    @Test
//    public void should_throw_exception_when_user_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> userServiceMock.deleteById(50));
//    }
//}
