//package org.example.integration;
//
//import org.example.config.TestConfig;
//import org.example.configuration.WebInitializer;
//import org.example.entity.Project;
//import org.example.repository.ProjectRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
//@WebAppConfiguration
//
//public class ProjectControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    public static final String PROJECT_ENDPOINT = "/api/projects";
//    public static final String START_DATE = "10-02-2022";
//    public static final String END_DATE = "17-02-2022";
//    public static final String TEST_TITLE = "{\"title\":\"test-project\"}";
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        projectRepository.deleteAll();
//    }
//
//    @AfterEach
//    public void deleteData() {
//        projectRepository.deleteAll();
//    }
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void createProjectSuccessTest() throws Exception {
//        mockMvc
//                .perform(
//                        post(PROJECT_ENDPOINT + "/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_TITLE))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//
//                .andExpect(jsonPath("$.title").value("test-project"))
//                .andExpect(jsonPath("$.start_date").value("10-02-2022"))
//                .andExpect(jsonPath("$.end_date").value("17-02-2022"))
//                .andExpect(jsonPath("$.id").isNotEmpty());
//    }
//
//    //    @Test
////    public void createProjectInternalServerErrorStatusTest() throws Exception {
////        departmentRepository.save(Department.builder().title("java-department").build());
////        mockMvc
////                .perform(
////                        post(DEPARTMENT_ENDPOINT + "/")
////                                .contentType(MediaType.APPLICATION_JSON)
////                                .content(JAVA_DEPARTMENT))
////                .andDo(print())
////                .andExpect(status().isInternalServerError());
////    }
////
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void createProjectClientErrorStatusTest() throws Exception {
//        projectRepository.save(Project.builder().title("test-project").build());
//        mockMvc
//                .perform(
//                        post(PROJECT_ENDPOINT + "/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_TITLE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
//    public void findProjectByIdSuccessTest() throws Exception {
//        Project project =
//                projectRepository.save(
//                        Project.builder().id(1).title("test-project").build());
//
//        mockMvc
//                .perform(get(PROJECT_ENDPOINT + "/{id}", project.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("test-project"));
//    }
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
//    public void findProjectByIdNotFoundStatusTest() throws Exception {
//        mockMvc.perform(get(PROJECT_ENDPOINT + "/1")).andDo(print()).andExpect(status().isNotFound());
//    }
//
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void updateProjectByIdSuccessTest() throws Exception {
//        Project oldProject =
//                projectRepository.save(Project.builder().title("test-project").build());
//        String updatedProject = "{\"title\":\"test2\"}";
//        mockMvc
//                .perform(
//                        put(PROJECT_ENDPOINT + "/{id}", oldProject.getId())
//                                .content(updatedProject)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.title").value("test2"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void updateProjectByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(
//                        put(PROJECT_ENDPOINT + "/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_TITLE))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void deleteProjectByIdSuccessTest() throws Exception {
//        Project project =
//                projectRepository.save(Project.builder().title("test-project").build());
//        mockMvc
//                .perform(
//                        delete(PROJECT_ENDPOINT + "/{id}", project.getId())
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    public void deleteProjectByIdNotFoundStatusTest() throws Exception {
////        mockMvc
////                .perform(delete(DEPARTMENT_ENDPOINT + "/100").contentType(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isNotFound());
////    }
//
//
//    @Test
////    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
//    public void getListOfProjectsSuccessTest() throws Exception {
//        List<Project> projects =
//                Arrays.asList(
//                        Project.builder().title("test-project").build(),
//                        Project.builder().title("test1-project").build(),
//                        Project.builder().title("test2-project").build());
//        projectRepository.saveAll(projects);
//
//        mockMvc
//                .perform(get(PROJECT_ENDPOINT + "/").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[*]", hasSize(3)));
//
//    }
//}
