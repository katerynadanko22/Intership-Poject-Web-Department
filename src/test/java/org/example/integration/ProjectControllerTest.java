package org.example.integration;

import org.example.config.TestConfig;
import org.example.configuration.WebInitializer;
import org.example.entity.Project;
import org.example.repository.ProjectRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class ProjectControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ProjectRepository projectRepository;

    private MockMvc mockMvc;

    public static final String PROJECT_ENDPOINT = "/api/projects/";
    public static final String TEST_ENTITY = "{\"title\":\"test-project\", \"startDate\":\"20-01-2022\", " +
            "\"endDate\":\"20-02-2022\"}";
    public static final String TEST_NAME = "{\"title\":\"test-project\"}";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        projectRepository.deleteAll();
    }

    @AfterEach
    public void deleteData() {
        projectRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
    public void createProjectSuccessTest() throws Exception {

        mockMvc
                .perform(
                        post(PROJECT_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.title").value("test-project"))
                .andExpect(jsonPath("$.startDate").value("20-01-2022"))
                .andExpect(jsonPath("$.endDate").value("20-02-2022"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void createProjectBadRequestErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        post(PROJECT_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_NAME))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
    public void createProjectClientErrorStatusTest() throws Exception {
        mockMvc
                .perform(
                        post(PROJECT_ENDPOINT)
                                .contentType(TEST_ENTITY)
                                .content(TEST_ENTITY))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"read"})
    public void findProjectByIdSuccessTest() throws Exception {
        Project project =
                projectRepository.save(
                        Project.builder().id(1).title("test-project").startDate(LocalDate.now())
                                .endDate(LocalDate.now()).build());

        mockMvc
                .perform(get(PROJECT_ENDPOINT + "{id}", project.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("test-project"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"read"})
    public void findProjectByIdNotFoundStatusTest() throws Exception {
        mockMvc.perform(get(PROJECT_ENDPOINT + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void updateProjectByIdSuccessTest() throws Exception {
        Project oldProject =
                projectRepository.save(Project.builder().title("bad name")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now())
                        .build());
        mockMvc
                .perform(
                        put(PROJECT_ENDPOINT + "{id}", oldProject.getId())
                                .content(TEST_ENTITY)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("test-project"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void updateProjectByIdNotFoundStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(PROJECT_ENDPOINT + "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TEST_ENTITY))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void deleteProjectByIdSuccessTest() throws Exception {
        Project project =
                projectRepository.save(Project.builder().title("test-project").startDate(LocalDate.now())
                        .endDate(LocalDate.now()).build());
        mockMvc
                .perform(
                        delete(PROJECT_ENDPOINT + "{id}", project.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void deleteProjectByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(delete(PROJECT_ENDPOINT + "100").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }

    @Test
    @WithMockUser(username = "admin@mail.com", authorities = {"write"})
    public void getListOfProjectsSuccessTest() throws Exception {
        List<Project> projects =
                Arrays.asList(
                        Project.builder().title("test-project").startDate(LocalDate.now())
                                .endDate(LocalDate.now()).build(),
                        Project.builder().title("test1-project").startDate(LocalDate.now())
                                .endDate(LocalDate.now()).build(),
                        Project.builder().title("test2-project").startDate(LocalDate.now())
                                .endDate(LocalDate.now()).build());
        projectRepository.saveAll(projects);

        mockMvc
                .perform(get(PROJECT_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)));
    }
}
