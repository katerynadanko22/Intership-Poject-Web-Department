//package org.example.controller;
//
//import org.example.configuration.AppConfiguration;
//import org.example.configuration.TestConfig;
//import org.example.entity.Department;
//import org.example.repository.DepartmentRepository;
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
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestConfig.class, AppConfiguration.class})
//@WebAppConfiguration
//class DepartmentControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    public static final String DEPARTMENT_URL = "/api/departments";
//    public static final String CREATE_IT_DEPARTMENT = "{\"title\":\"it\"}";
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
//    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
//    public void createDepartmentSuccessTest() throws Exception {
//        mockMvc
//                .perform(
//                        post(DEPARTMENT_URL)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(CREATE_IT_DEPARTMENT))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//
//                .andExpect(jsonPath("$.title").value("it"))
//                .andExpect(jsonPath("$.id").isNotEmpty());
//    }
//
//    @Test
//    public void createDepartmentInternalServerErrorStatusTest() throws Exception {
//        departmentRepository.save(Department.builder().title("it").build());
//        mockMvc
//                .perform(
//                        post(DEPARTMENT_URL)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(CREATE_IT_DEPARTMENT))
//                .andDo(print())
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    @WithMockUser(username = "user@mail.com", roles = {"USER"})
//    public void findDepartmentByIdSuccessTest() throws Exception {
//        Department department =
//                departmentRepository.save(
//                        Department.builder().id(1).title("it").build());
//
//        mockMvc
//                .perform(get(DEPARTMENT_URL + "/{id}", department.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("it"));
//    }
//
//    @Test
//    public void findDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc.perform(get(DEPARTMENT_URL + "/1")).andDo(print()).andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    public void updateDepartmentByIdSuccessTest() throws Exception {
//        Department oldDepartment =
//                departmentRepository.save(Department.builder().title("hr").build());
//        String updatedDepartment = "{\"title\":\"dev\"}";
//        mockMvc
//                .perform(
//                        put(DEPARTMENT_URL + "/{id}", oldDepartment.getId())
//                                .content(updatedDepartment)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.title").value("dev"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void updateDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(
//                        put(DEPARTMENT_URL + "/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(CREATE_IT_DEPARTMENT))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteDepartmentByIdSuccessTest() throws Exception {
//        Department department =
//                departmentRepository.save(Department.builder().title("it").build());
//        mockMvc
//                .perform(
//                        delete(DEPARTMENT_URL + "/{id}", department.getId())
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(delete(DEPARTMENT_URL + "/1").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void getListOfDepartmentsSuccessTest() throws Exception {
//        List<Department> departmentList =
//                Arrays.asList(
//                        Department.builder().title("it").build(),
//                        Department.builder().title("hr").build(),
//                        Department.builder().title("dev").build());
//        departmentRepository.saveAll(departmentList);
//
//        mockMvc
//                .perform(get(DEPARTMENT_URL).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[*]", hasSize(3)));
//
//    }
//}