package org.example;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.entity.Department;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.UserRepository;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserServiceImpl userServiceMock;

    @Test
    public void testSaveUser()  {

        User userKate = new User(10,"Kate", "Danko",
                "kateryna@mali.com", "katekate", "Jun",
                new Department(1, "dev"));
        when(userRepositoryMock.save(userKate)).thenReturn(userKate);
        User savedUser = userServiceMock.save(userKate);
        assertEquals("Kate", savedUser.getFirstName());
        assertThat(savedUser.getId()).isGreaterThan(0);
    }


    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepositoryMock.findAll()).thenReturn(users);
        List<User> users2 = userServiceMock.findAll();
        assertThat(users.equals(users2));
    }

    @Test
    public void whenSaveUser_shouldReturnUser() {
        User user = new User();
        user.setFirstName("Testname");
        when(userRepositoryMock.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userServiceMock.save(user);
        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
        verify(userRepositoryMock).save(user);
    }

    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        User user = new User();
        user.setLastName("Testname");
        user.setId(1);
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        userServiceMock.deleteById(user.getId());
        verify(userRepositoryMock).deleteById(user.getId());
    }

    @Test
    public void should_throw_exception_when_user_do_exist() {
        User user = new User();
        user.setId(89);
        user.setLastName("Testname");
        given(userRepositoryMock.findById(89)).willReturn(Optional.ofNullable(null));
        userServiceMock.deleteById(user.getId());
    }

//    @Test(expected = RuntimeException.class)
//    public void should_throw_exception_when_user_doesnt_exist() {
//        User user = new User();
//        user.setId(89);
//        user.setLastName("Testname");
//        given(userRepositoryMock.findById((int) anyLong())).willReturn(Optional.ofNullable(null));
//        userServiceMock.deleteById(user.getId());
//    }

//    @Test
//    public void whenGivenId_shouldReturnUser_ifFound() {
//        User user = new User();
//        user.setId(89);
//        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
//        User expected = userServiceMock.getById(user.getId());
//        assertThat(expected).isSameAs(user);
//        verify(userRepositoryMock).findById(user.getId());
//    }

//    @Test(expected = ResourceNotFoundException.class)
//    public void should_throw_exception_when_user_doesnt_exist() {
//        User user = new User();
//        user.setId(89);
//        user.setFirstName("TestName");
//
//        given(userRepositoryMock.findById((int) anyLong())).willReturn(Optional.ofNullable(null));
//        userServiceMock.getById(user.getId());
//    }

}
