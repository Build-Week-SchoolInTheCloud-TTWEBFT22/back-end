package com.lambdaschool.schoolinthecloud.controllers;

import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.services.UserService;
import com.lambdaschool.schoolinthecloud.services.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class UserTasksController {
    @Autowired
    UserTaskService userTaskService;

    // http://localhost:2019/tasks/tasks
    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<?> findAllTasks() {
        List<UserTasks> userList = userTaskService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
