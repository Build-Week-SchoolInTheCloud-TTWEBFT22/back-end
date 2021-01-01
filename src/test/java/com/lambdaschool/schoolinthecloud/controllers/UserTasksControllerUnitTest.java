package com.lambdaschool.schoolinthecloud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.schoolinthecloud.SchoolInTheCloudApplication;
import com.lambdaschool.schoolinthecloud.models.Role;
import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserRoles;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.services.UserTaskService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SchoolInTheCloudApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin",
        roles = {"USER", "ADMIN"})
public class UserTasksControllerUnitTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    UserTaskService userTaskService;

    private List<UserTasks> userTasksList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        //Mock role
        Role r1 = new Role("volunteer");
        r1.setRoleid(1);

        //Mock user
        User u3 = new User("volunteer",
                "password",
                "volunteer@lambdaschool.local");
        u3.setUserid(10);

        u3.getRoles()
                .add(new UserRoles(u3,
                        r1));
        u3.getUsertasks()
                .add(new UserTasks( u3, "Teach Music"));
        u3.getUsertasks()
                .get(0)
                .setTaskid(10);

        u3.setCountry("USA");
        u3.setAvailability("1-2pm PST");

        UserTasks mockTask = new UserTasks(u3,"Teach Science");
        mockTask.setTaskid(10);
        userTasksList.add(mockTask);

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllTasks() throws Exception {
        String apiUrl = "/tasks/tasks";

        Mockito.when(userTaskService.findAll()).thenReturn(userTasksList);

        System.out.println(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userTasksList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List",
                er,
                tr);
    }

    @Test
    public void getTaskById() throws Exception {
        String apiUrl = "/tasks/task/10";

        Mockito.when(userTaskService.findUsertasksById(10))
                .thenReturn(userTasksList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userTasksList.get(0));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List",
                er,
                tr);
    }

    @Test
    public void deleteTask() throws Exception {
        String apiUrl = "/tasks/task/{taskid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl,
                "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTask() throws Exception {
        String apiUrl = "/tasks/task/{taskid}/description/{taskdescription}}";

        Mockito.when(userTaskService.update(any(Long.class),
                any(String.class)))
                .thenReturn(userTasksList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl,
                100, "Test");

        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addTask() throws Exception {
        String apiUrl = "/tasks/task/{userid}/description/{taskdescription}";

        Mockito.when(userTaskService.save(any(Long.class), any(String.class)))
                .thenReturn(userTasksList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl, 10L, "Test");

        mockMvc.perform(rb)
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
}