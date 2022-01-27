package org.example;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.example.entity.Department;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
    public void testListUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepositoryMock.findAll()).thenReturn(users);
        List<User> users2 = userServiceMock.findAll();
//        assertEquals(1, users.size());
        assertThat(users.equals(users2));

    }
//    @Test
//    public void testFindUserById() {
//        User user = userServiceMock.findById(2).get();
//        when(userRepositoryMock.findById(2)).thenReturn(Optional.of(user));
//        System.out.println(user);
//        assertThat(user).isNotNull();
//    }
//    @Test
//    public void testUpdateUserDetails() {
//        User user = userServiceMock.findById(2).get();
//        when(userRepositoryMock.findById(2)).thenReturn(Optional.of(user));
//        user.setFirstName("Bob");
//        user.setEmail("kate1111@mail.com");
//
//        userServiceMock.save(user);
//    }

    //    private DepartmentDtoMapper mapper
//            = Mappers.getMapper(DepartmentDtoMapper.class);
//    @Test
//    public void givenDepartmentToDepartmentDto_whenMaps_thenCorrect() {
//        Department department = new Department();
//        department.setTitle("DepartmentName1");
//        DepartmentDTO departmentDTO = mapper.toDepartmentDto(department);
//
//        assertEquals(department.getTitle(), departmentDTO.getTitle());
//
//    }
//    @Test
//    public void givenDepartmentDtoToDepartment_whenMaps_thenCorrect() {
//        DepartmentDTO departmentDTO = new DepartmentDTO();
//        departmentDTO.setTitle("DepartmentName2");
//        Department department = mapper.toDepartment(departmentDTO);
//        assertEquals(departmentDTO.getTitle(), department.getTitle());
//    }
}
