package com.lambdaschool.schoolinthecloud.services;

import com.lambdaschool.schoolinthecloud.SchoolInTheCloudApplication;
import com.lambdaschool.schoolinthecloud.exceptions.ResourceNotFoundException;
import com.lambdaschool.schoolinthecloud.models.Role;
import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserRoles;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.repository.UserRepository;
import com.lambdaschool.schoolinthecloud.repository.UserTaskRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolInTheCloudApplication.class)
public class UserTaskServiceImplUnitTest {

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private UserRepository userrepos;

    @MockBean
    private UserTaskRepository usertaskrepos;

    @MockBean
    HelperFunctions helperFunctions;

    private List<User> userList = new ArrayList<>();
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
        userList.add(u3);

        UserTasks mockTask = new UserTasks(u3,"Teach Science");
        mockTask.setTaskid(10);
        userTasksList.add(mockTask);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        Mockito.when(usertaskrepos.findAll())
                .thenReturn(userTasksList);

        assertEquals(1, userTasksList.size());
    }

    @Test
    public void findUsertasksById() {
        Mockito.when(usertaskrepos.findById(10L))
                .thenReturn(Optional.of(userTasksList.get(0)));

        assertEquals("Teach Science",
                userTaskService.findUsertasksById(10L)
                        .getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findUsertasksByIdNotFound() {
        Mockito.when(usertaskrepos.findById(100L))
                .thenReturn(Optional.empty());

        assertEquals("Teach Science",
                userTaskService.findUsertasksById(100L)
                        .getDescription());
    }

    @Test
    public void delete() {
        Mockito.when(usertaskrepos.findById(10L))
                .thenReturn(Optional.of(userTasksList.get(0)));

        Mockito.doNothing()
                .when(usertaskrepos)
                .deleteById(10L);

        userTaskService.delete(10L);

        assertEquals(1, userTasksList.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotFound() {
        Mockito.when(usertaskrepos.findById(100L))
                .thenReturn(Optional.empty());

        Mockito.doNothing()
                .when(usertaskrepos)
                .deleteById(100L);

        userTaskService.delete(100L);

        assertEquals(1, userTasksList.size());
    }

    @Test
    public void update() {
        Mockito.when(usertaskrepos.findById(10L))
                .thenReturn(Optional.of(userTasksList.get(0)));

        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
                .thenReturn(true);

        Mockito.when(usertaskrepos.save(any(UserTasks.class)))
                .thenReturn(userTasksList.get(0));

        assertEquals("Teach String Theory",
                userTaskService.update(10L,
                        "Teach String Theory")
                        .getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateNotFound() {
        Mockito.when(usertaskrepos.findById(100L))
                .thenReturn(Optional.empty());

        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
                .thenReturn(true);

        Mockito.when(usertaskrepos.save(any(UserTasks.class)))
                .thenReturn(userTasksList.get(0));

        assertEquals("Teach String Theory",
                userTaskService.update(10L,
                        "Teach String Theory")
                        .getDescription());
    }

    @Test
    public void save() {
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

        Mockito.when(userrepos.findById(10L))
                .thenReturn(Optional.of(u3));

        Mockito.when(usertaskrepos.save(any(UserTasks.class)))
                .thenReturn(mockTask);

        assertEquals("Teach Science",
                userTaskService.save(10L, "Teach Science")
                        .getDescription());
    }
}