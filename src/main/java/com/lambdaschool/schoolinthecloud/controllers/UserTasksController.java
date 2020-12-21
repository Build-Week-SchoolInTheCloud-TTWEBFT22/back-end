package com.lambdaschool.schoolinthecloud.controllers;

import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.services.UserService;
import com.lambdaschool.schoolinthecloud.services.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // DELETE http://localhost:2019/tasks/task/{taskid}
    // Removes the task with the provided taskid
    @DeleteMapping(value = "/task/{taskid}", produces = "application/json")
    public ResponseEntity<?> findAllTasks(@PathVariable long taskid) {
        userTaskService.delete(taskid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
