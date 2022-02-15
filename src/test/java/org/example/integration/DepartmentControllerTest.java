package org.example.integration;

import org.example.config.TestConfig;
import org.example.configuration.WebInitializer;
import org.example.entity.Department;
import org.example.repository.DepartmentRepository;
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

class DepartmentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private DepartmentRepository departmentRepository;

    public static final String DEPARTMENT_ENDPOINT = "/api/departments";
    public static final String JAVA_DEPARTMENT = "{\"title\":\"java-department\"}";

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        departmentRepository.deleteAll();
    }

    @AfterEach
    public void deleteData() {
        departmentRepository.deleteAll();
    }

    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void createDepartmentSuccessTest() throws Exception {
        mockMvc
                .perform(
                        post(DEPARTMENT_ENDPOINT + "/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JAVA_DEPARTMENT))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.title").value("java-department"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

//    @Test
//    public void createDepartmentInternalServerErrorStatusTest() throws Exception {
//        departmentRepository.save(Department.builder().title("java-department").build());
//        mockMvc
//                .perform(
//                        post(DEPARTMENT_ENDPOINT + "/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JAVA_DEPARTMENT))
//                .andDo(print())
//                .andExpect(status().isInternalServerError());
//    }
//
    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void createDepartmentClientErrorStatusTest() throws Exception {
        departmentRepository.save(Department.builder().title("java-department").build());
        mockMvc
                .perform(
                        post(DEPARTMENT_ENDPOINT + "/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JAVA_DEPARTMENT))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    public void findDepartmentByIdSuccessTest() throws Exception {
        Department department =
                departmentRepository.save(
                        Department.builder().id(1).title("java-department").build());

        mockMvc
                .perform(get(DEPARTMENT_ENDPOINT + "/{id}", department.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("java-department"));
    }

    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    public void findDepartmentByIdNotFoundStatusTest() throws Exception {
        mockMvc.perform(get(DEPARTMENT_ENDPOINT + "/1")).andDo(print()).andExpect(status().isNotFound());
    }


    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void updateDepartmentByIdSuccessTest() throws Exception {
        Department oldDepartment =
                departmentRepository.save(Department.builder().title("hr").build());
        String updatedDepartment = "{\"title\":\"dev\"}";
        mockMvc
                .perform(
                        put(DEPARTMENT_ENDPOINT + "/{id}", oldDepartment.getId())
                                .content(updatedDepartment)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("dev"))
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void updateDepartmentByIdNotFoundStatusTest() throws Exception {
        mockMvc
                .perform(
                        put(DEPARTMENT_ENDPOINT + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JAVA_DEPARTMENT))
                .andExpect(status().isNotFound());
    }

    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void deleteDepartmentByIdSuccessTest() throws Exception {
        Department department =
                departmentRepository.save(Department.builder().title("java-department").build());
        mockMvc
                .perform(
                        delete(DEPARTMENT_ENDPOINT + "/{id}", department.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void deleteDepartmentByIdNotFoundStatusTest() throws Exception {
//        mockMvc
//                .perform(delete(DEPARTMENT_ENDPOINT + "/100").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }


    @Test
//    @WithMockUser(username = "admin@mail.com", roles = {"ROLE_ADMIN"})
    public void getListOfDepartmentsSuccessTest() throws Exception {
        List<Department> departmentList =
                Arrays.asList(
                        Department.builder().title("java-department").build(),
                        Department.builder().title("HR").build(),
                        Department.builder().title("dev").build());
        departmentRepository.saveAll(departmentList);

        mockMvc
                .perform(get(DEPARTMENT_ENDPOINT + "/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)));

    }
}