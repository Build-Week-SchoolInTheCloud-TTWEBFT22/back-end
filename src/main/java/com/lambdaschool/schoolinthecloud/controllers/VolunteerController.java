package com.lambdaschool.schoolinthecloud.controllers;

import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {
    @Autowired
    UserService userService;

    // http://localhost:2019/volunteers/volunteers
    @GetMapping(value = "/volunteers", produces = "application/json")
    public ResponseEntity<?> findAllVolunteers() {
        List<User> userList = userService.getVolunteers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
