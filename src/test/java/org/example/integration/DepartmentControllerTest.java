//package org.example.integration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.example.config.TestConfig;
//import org.example.configuration.WebInitializer;
//import org.example.entity.Department;
//import org.example.repository.DepartmentRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
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
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
//@WebAppConfiguration
//class DepartmentControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Value("${department.endpoint}")
//    public String DEPARTMENT_ENDPOINT;
//    @Value("${department.endpoint.wrong}")
//    public String DEPARTMENT_ENDPOINT_WRONG;
//    @Value("${department.test.entity}")
//    public String TEST_ENTITY_DEPARTMENT;
//
//    @Value("${department.test.id}")
//    public String TEST_ID;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        departmentRepository.deleteAll();
//    }
//
//    @AfterEach
//    public void deleteData() {
//        departmentRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
//    public void createDepartmentSuccessTest() throws Exception {
//        mockMvc
//                .perform(
//                        post(DEPARTMENT_ENDPOINT)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_ENTITY_DEPARTMENT))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//
//                .andExpect(jsonPath("$.title").value("java-department"))
//                .andExpect(jsonPath("$.id").isNotEmpty());
//    }
//
//    @Test
//    public void createDepartmentClientBadRequestErrorStatusTest() throws Exception {
//        mockMvc
//                .perform(
//                        post(DEPARTMENT_ENDPOINT)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_ID))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = "read")
//    public void findDepartmentByIdSuccessTest() throws Exception {
//        Department department =
//                departmentRepository.save(
//                        Department.builder().id(1).title("java-department").build());
//
//        mockMvc
//                .perform(get(DEPARTMENT_ENDPOINT + "{id}", department.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("java-department"));
//    }
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = "read")
//    public void findDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc.perform(get(DEPARTMENT_ENDPOINT_WRONG))
//                .andDo(print())
//                .andExpect(status()
//                        .isNotFound());
//    }
//
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
//    public void updateDepartmentByIdSuccessTest() throws Exception {
//        Department oldDepartment =
//                departmentRepository.save(Department.builder().title("hr").build());
//        mockMvc
//                .perform(
//                        put(DEPARTMENT_ENDPOINT + "{id}", oldDepartment.getId())
//                                .content(TEST_ENTITY_DEPARTMENT)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.title").value("java-department"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
//    public void updateDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(
//                        put(DEPARTMENT_ENDPOINT_WRONG)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(TEST_ENTITY_DEPARTMENT))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = {"read", "write"})
//    public void deleteDepartmentByIdSuccessTest() throws Exception {
//        Department department =
//                departmentRepository.save(Department.builder().title("java-department").build());
//        mockMvc
//                .perform(
//                        delete(DEPARTMENT_ENDPOINT + "{id}", department.getId())
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(delete(DEPARTMENT_ENDPOINT_WRONG)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    @WithMockUser(username = "admin@mail.com", authorities = "read")
//    public void getListOfDepartmentsSuccessTest() throws Exception {
//        List<Department> departmentList =
//                Arrays.asList(
//                        Department.builder().title("java-department").build(),
//                        Department.builder().title("HR").build(),
//                        Department.builder().title("dev").build());
//        departmentRepository.saveAll(departmentList);
//
//        mockMvc
//                .perform(get(DEPARTMENT_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[*]", hasSize(3)));
//
//    }
//}