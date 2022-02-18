package org.example.integration;

import org.example.config.TestConfig;
import org.example.configuration.WebInitializer;
import org.example.entity.Department;
import org.example.entity.Role;
import org.example.entity.Status;
import org.example.entity.User;
import org.example.repository.DepartmentRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private MockMvc mockMvc;

    public static final String USER_ENDPOINT = "/api/users/";
    public static final String TEST_ENTITY = "{\"firstName\":\"Kate\",\"lastName\":\"Danko\",\"email\":\"kate\"," +
            "\"password\":\"user\", \"jobTitle\":\"junior\",\"status\":\"ACTIVE\",\"role\":\"ROLE_USER\"}";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @AfterEach
    public void deleteData() {
        userRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void registerUserSuccessTest() throws Exception {
        Department department = departmentRepository.save(Department.builder().title("Java-dep").build());
        mockMvc
                .perform(
                        post(USER_ENDPOINT + "{departmentId}", department.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY)
                                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kate"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void registerUserClientErrorStatusTest() throws Exception {
        Department department = departmentRepository.save(Department.builder().title("Java-dep").build());
        User user = User.builder().firstName("Kate").lastName("Danko").email("kate").password("user")
                .jobTitle("junior").status(Status.valueOf("ACTIVE")).role(Role.valueOf("ROLE_USER")).build();
        user.setDepartment(department);
        userRepository.save(user);
        mockMvc
                .perform(
                        post(USER_ENDPOINT + "{departmentId}", department.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void findUserByIdSuccessTest() throws Exception {
        Department department = departmentRepository.save(Department.builder().title("Java-dep").build());
        User user = userRepository.save(User.builder()
                .firstName("Kate")
                .lastName("Danko")
                .email("kate")
                .password("user")
                .jobTitle("junior")
                .status(Status.valueOf("ACTIVE"))
                .role(Role.valueOf("ROLE_USER"))
                .build());
        user.setDepartment(department);
        userRepository.save(user);
        mockMvc
                .perform(get(USER_ENDPOINT + "{id}", user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kate"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void findUserByIdNotFoundStatusTest() throws Exception {
        mockMvc.perform(get(USER_ENDPOINT + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void updateUserByIdSuccessTest() throws Exception {
        Department department = departmentRepository.save(Department.builder().title("Java-dep").build());
        User oldUser =
                userRepository.save(User.builder()
                        .firstName("Alex")
                        .lastName("Danko")
                        .email("kate")
                        .password("user")
                        .jobTitle("junior")
                        .status(Status.valueOf("ACTIVE"))
                        .role(Role.valueOf("ROLE_USER"))
                        .build());

        mockMvc
                .perform(
                        put(USER_ENDPOINT + "{id}", oldUser.getId())
                                .content(TEST_ENTITY)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Kate"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void updateUserByIdClientErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(USER_ENDPOINT + "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void updateUserDepartmentByIdClientErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(USER_ENDPOINT + "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void deleteUserByIdSuccessTest() throws Exception {
        Department department = departmentRepository.save(new Department(1, "dev"));
        User user = userRepository.save(User.builder()
                .firstName("Kate")
                .lastName("Danko")
                .email("kate")
                .password("user")
                .jobTitle("junior")
                .status(Status.valueOf("ACTIVE"))
                .role(Role.valueOf("ROLE_USER"))
                .department(department)
                .build());
        mockMvc
                .perform(
                        delete(USER_ENDPOINT + "{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void getListOfUsersSuccessTest() throws Exception {
        Department department = departmentRepository.save(Department.builder().title("Java-dep").build());
        User user = userRepository.save(User.builder()
                .firstName("Kate")
                .lastName("Danko")
                .email("kate")
                .password("user")
                .jobTitle("junior")
                .status(Status.valueOf("ACTIVE"))
                .role(Role.valueOf("ROLE_USER"))
                .build());
        user.setDepartment(department);
        User user1 = userRepository.save(User.builder()
                .firstName("Kate")
                .lastName("Danko")
                .email("kate1")
                .password("user")
                .jobTitle("junior")
                .status(Status.valueOf("ACTIVE"))
                .role(Role.valueOf("ROLE_USER"))
                .build());
        user.setDepartment(department);
        userRepository.save(user);
        User user2 = userRepository.save(User.builder()
                .firstName("Kate")
                .lastName("Danko")
                .email("kate2")
                .password("user")
                .jobTitle("junior")
                .status(Status.valueOf("ACTIVE"))
                .role(Role.valueOf("ROLE_USER"))
                .build());
        user.setDepartment(department);
        List<User> users = Arrays.asList(user, user1, user2);
        userRepository.saveAll(users);

        mockMvc
                .perform(get(USER_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)));
    }
}

