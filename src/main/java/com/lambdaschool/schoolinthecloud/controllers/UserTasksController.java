//package com.lambdaschool.schoolinthecloud.controllers;
//
//import com.lambdaschool.schoolinthecloud.models.User;
//import com.lambdaschool.schoolinthecloud.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(name = "/tasks")
//public class UserTasksController {
//    @Autowired
//    UserService userService;
//
//    // http://localhost:2019/volunteers/volunteers
//    @GetMapping(name = "/volunteers/", produces = "application/json")
//    public ResponseEntity<?> findAllVolunteers() {
//        List<User> userList = userService.findAll();
//
//        return new ResponseEntity<>(userList, HttpStatus.OK);
//    }
//}
