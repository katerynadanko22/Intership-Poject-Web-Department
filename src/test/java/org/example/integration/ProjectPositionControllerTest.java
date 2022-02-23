package org.example.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

@PropertySource(value = {"classpath:tests.properties"})
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
    @Value("${project.position.endpoint}")
    public String PROJECT_POSITION_ENDPOINT;
    @Value("${project.position.endpoint.wrong}")
    public String PROJECT_POSITION_ENDPOINT_WRONG;
    @Value("${project.position.test.entity}")
    public String TEST_ENTITY_PROJECT_POSITION;
    @Value("${project.position.test.name}")
    public String TEST_NAME_PROJECT_POSITION;

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

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write", "read"})
    public void createProjectPositionSuccessTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Project project = new Project();
        project.setTitle("Java project");
        projectRepository.save(project);
        String valueProjectAsString = objectMapper.writeValueAsString(project);
        Project projectFromDB = objectMapper.readValue(valueProjectAsString, Project.class);

        Department department = new Department();
        department.setTitle("Java-department");
        departmentRepository.save(department);
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test");
        user.setPassword("test");
        user.setJobTitle("test");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.ROLE_USER);
        user.setDepartment(department);
        userRepository.save(user);
        String valueUserAsString = objectMapper.writeValueAsString(user);
        User userFromDB = objectMapper.readValue(valueUserAsString, User.class);
        ProjectPosition projectPosition = new ProjectPosition( "Java-developer",
                userFromDB, projectFromDB);
        mockMvc
                .perform(
                        post(PROJECT_POSITION_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(projectPosition))
                                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.positionTitle").value("Java-developer"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void createProjectPositionClientErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        post(PROJECT_POSITION_ENDPOINT + "/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY_PROJECT_POSITION))
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
    public void createProjectPositionBadRequestErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        post(PROJECT_POSITION_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("title"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void findProjectPositionByIdNotFoundStatusTest() throws Exception {
        mockMvc.perform(get(PROJECT_POSITION_ENDPOINT + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void updateProjectPositionByIdSuccessTest() throws Exception {

        ProjectPosition oldProjectPosition =
                projectPositionRepository.save(ProjectPosition.builder()
                        .positionTitle("test old")
                        .build());
        mockMvc
                .perform(
                        put(PROJECT_POSITION_ENDPOINT + "/{id}", oldProjectPosition.getId())
                                .content(TEST_NAME_PROJECT_POSITION)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.positionTitle").value("test"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void updateProjectPositionByIdNotFoundStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(PROJECT_POSITION_ENDPOINT_WRONG)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY_PROJECT_POSITION))
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

