package org.example.integration;

import org.example.config.TestConfig;
import org.example.configuration.WebInitializer;
import org.example.entity.Department;
import org.example.entity.Project;
import org.example.entity.ProjectPosition;
import org.example.entity.Role;
import org.example.entity.Status;
import org.example.entity.User;
import org.example.repository.DepartmentRepository;
import org.example.repository.ProjectPositionRepository;
import org.example.repository.ProjectRepository;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class ProjectPositionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ProjectPositionRepository projectPositionRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private MockMvc mockMvc;

    public static final String PROJECT_POSITION_ENDPOINT = "/api/project-positions/";
    public static final String TEST_ENTITY = "{\"positionTitle\":\"test\"," +
            "\"positionStartDate\":\"20-01-2022\", \"positionEndDate\":\"20-02-2022\"}";
    public static final String PROJECT_ID = "{\"project\":\"1\"}";
    public static final String PROJECT_ENTITY = "{\"project\":\"1\"}";
    public static final String USER_ID = "{\"user\":\"1\"}";
    public static final String USER_ENTITY = "{\"user\":\"1\"}";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        projectPositionRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @AfterEach
    public void deleteData() {
        projectPositionRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
        departmentRepository.deleteAll();
    }

//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
//    public void createProjectPositionSuccessTest() throws Exception {
//        Project project = projectRepository.save( Project.builder().id(1).title("test-project").startDate(LocalDate.now())
//                .endDate(LocalDate.now()).build());
//        User user = userRepository.save(User.builder().firstName("Kate").lastName("Danko").email("kate").password("user")
//                .jobTitle("junior").status(Status.valueOf("ACTIVE")).role(Role.valueOf("ROLE_USER")).build());
//        mockMvc
//                .perform(
//                        post(PROJECT_POSITION_ENDPOINT  + "{projectId}" +"/{userId}", user.getId(), project.getId())
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_ENTITY)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//
//                .andExpect(jsonPath("$.positionTitle").value("test"))
//                .andExpect(jsonPath("$.positionStartDate").value("20-01-2022"))
//                .andExpect(jsonPath("$.positionEndDate").value("20-02-2022"))
//                .andExpect(jsonPath("$.project").value(1))
//                .andExpect(jsonPath("$.user").value(1))
//                .andExpect(jsonPath("$.id").isNotEmpty());
//    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void createProjectPositionClientErrorStatusTest() throws Exception {
        Project project = projectRepository.save(new Project(1,"test name", LocalDate.now(), LocalDate.now()));
        Department department = departmentRepository.save(new Department(1, "dev"));
        User user = userRepository.save(new User(1, "TestName", "Danko", "kateryna@mali.com",
                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, department));
        projectPositionRepository.save(ProjectPosition.builder()
                .positionTitle("test")
                .positionStartDate(LocalDate.now())
                .positionEndDate(LocalDate.now())
                .project(project)
                .user(user)
                .build());
        mockMvc
                .perform(
                        post(PROJECT_POSITION_ENDPOINT + "/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void findProjectPositionByIdSuccessTest() throws Exception {
        Project project = projectRepository.save(new Project(1,"test name", LocalDate.now(), LocalDate.now()));
        Department department = departmentRepository.save(new Department(1, "dev"));
        User user = userRepository.save(new User(1, "TestName", "Danko", "kateryna@mali.com",
                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, department));
        ProjectPosition projectPosition =
                projectPositionRepository.save(
                        ProjectPosition.builder()
                                .id(1)
                                .positionTitle("test")
                                .positionStartDate(LocalDate.now())
                                .positionEndDate(LocalDate.now())
                                .project(project)
                                .user(user)
                                .build());
        mockMvc
                .perform(get(PROJECT_POSITION_ENDPOINT + "/{id}", projectPosition.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.positionTitle").value("test"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void findProjectPositionByIdNotFoundStatusTest() throws Exception {
        mockMvc.perform(get(PROJECT_POSITION_ENDPOINT + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
//    public void updateProjectPositionByIdSuccessTest() throws Exception {
//        Project project = projectRepository.save(new Project(1,"test name", LocalDate.now(), LocalDate.now()));
//        User user = userRepository.save(new User(1, "TestName", "Danko", "kateryna@mali.com",
//                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, new Department(1, "Java-dep")));
//
//        ProjectPosition oldProjectPosition =
//                projectPositionRepository.save(ProjectPosition.builder()
//                        .positionTitle("test old")
//                        .positionStartDate(LocalDate.now())
//                        .positionEndDate(LocalDate.now())
//                        .project(project)
//                        .user(user)
//                        .build());
//        mockMvc
//                .perform(
//                        put(PROJECT_POSITION_ENDPOINT + "/{id}", oldProjectPosition.getId())
//                                .content(TEST_ENTITY)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.positionTitle")
//                        .value("test new"))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void updateProjectPositionByIdNotFoundStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(PROJECT_POSITION_ENDPOINT + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void deleteProjectPositionByIdSuccessTest() throws Exception {
        Project project = projectRepository.save(new Project(1,"test name", LocalDate.now(), LocalDate.now()));
        Department department = departmentRepository.save(new Department(1, "dev"));
        User user = userRepository.save(new User(1, "TestName", "Danko", "kateryna@mali.com",
                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, department));
        ProjectPosition projectPosition =
                projectPositionRepository.save(ProjectPosition.builder()
                        .positionTitle("test")
                        .positionStartDate(LocalDate.now())
                        .positionEndDate(LocalDate.now())
                        .project(project)
                        .user(user)
                        .build());
        mockMvc
                .perform(
                        delete(PROJECT_POSITION_ENDPOINT + "/{id}", projectPosition.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void getListOfProjectPositionsSuccessTest() throws Exception {
        Project project = projectRepository.save(new Project(1,"test name", LocalDate.now(), LocalDate.now()));
        Department department = departmentRepository.save(new Department(1, "dev"));
        User user = userRepository.save(new User(1, "TestName", "Danko", "kateryna@mali.com",
                "katekate", "Jun", Status.ACTIVE, Role.ROLE_ADMIN, department));

        List<ProjectPosition> projectPositions =
                Arrays.asList(
                        ProjectPosition.builder().positionTitle("test").positionStartDate(LocalDate.now())
                                .positionEndDate(LocalDate.now()).project(project).user(user).build(),
                        ProjectPosition.builder().positionTitle("test1").positionStartDate(LocalDate.now())
                                .positionEndDate(LocalDate.now()).project(project).user(user).build(),
                        ProjectPosition.builder().positionTitle("test2").positionStartDate(LocalDate.now())
                                .positionEndDate(LocalDate.now()).project(project).user(user).build());
        projectPositionRepository.saveAll(projectPositions);

        mockMvc
                .perform(get(PROJECT_POSITION_ENDPOINT + "/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)));
    }
}

