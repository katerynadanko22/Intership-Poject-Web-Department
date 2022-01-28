package org.example;

import org.example.configuration.AppConfiguration;
import org.example.configuration.HibernateConfig;
import org.example.configuration.LiquiBaseConfig;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, AppConfiguration.class, LiquiBaseConfig.class})
@WebAppConfiguration
public class DepartmentControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
//    MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getAccount() throws Exception {
        Assert.assertNotNull(webApplicationContext);
//    this.mockMvc
//        .perform(get("/departments").accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
    }
}