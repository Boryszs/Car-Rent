package com.Server.controller;

import com.Server.dto.Request.EditUser;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/user")
@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Zmiana danych użytkownika
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUser editUser) {
        try {
            return new ResponseEntity<>(userService.update(editUser), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Error "+exceptionRequest.getErr()+"------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestParam Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Error User Not Exist ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
