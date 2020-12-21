package com.lambdaschool.schoolinthecloud.controllers;

import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.services.UserService;
import com.lambdaschool.schoolinthecloud.services.UserTaskService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class UserTasksController {
    @Autowired
    UserTaskService userTaskService;

    // GET http://localhost:2019/tasks/tasks
    // Gets all tasks in the system
    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<?> findAllTasks() {
        List<UserTasks> userList = userTaskService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // GET http://localhost:2019/tasks/task/{taskid}
    // Get specific task using the  provided taskid
    @GetMapping(value = "/task/{taskid}", produces = "application/json")
    public ResponseEntity<?> getTaskById(@PathVariable long taskid) {
        UserTasks task = userTaskService.findUsertasksById(taskid);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/tasks/task/{taskid}
    // Removes the task with the provided taskid
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/task/{taskid}", produces = "application/json")
    public ResponseEntity<?> deleteTask(@PathVariable long taskid) {
        userTaskService.delete(taskid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // POST http://localhost:2019/tasks/task/{userid}/description/{taskdescription}
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/task/{userid}/description/{taskdescription}", consumes = "application/json")
    public ResponseEntity<?> addTask(
        @PathVariable long userid,
        @PathVariable String taskdescription) {

        UserTasks newTask = userTaskService.save(userid, taskdescription);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/tasks/task/{taskid}")
                .buildAndExpand(newTask.getTaskid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

}
