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

/**
 * UserController is use to supports operations about database table User.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0.
 * @since 2020-12-29.
 */


@RequestMapping(value = "/user")
@RestController
@CrossOrigin
public class UserController {

    /**
     * Logger use to logger on server.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * UserService operation on database table User
     */
    private UserService userService;

    /**
     * Constructor
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Zmiana danych użytkownika
    /**
     * This method edit user data.
     * This method use endpoint /user/edit.
     * @param editUser new data user.
     * @return new data user Http.Status 200 or 400.
     * @exception ExceptionRequest when server catch Error.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUser editUser) {
        try {
            logger.info("------ User edited successfully ------");
            return new ResponseEntity<>(userService.update(editUser), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Error " + exceptionRequest.getErr() + "------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Usuwa User.
    /**
     * This method delete user on id.
     * This method use endpoint /user/delete.
     * @param id id user delete.
     * @return Http.Status 200.
     * @exception ExceptionRequest when user not exist.
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestParam Long id) {
        try {
            userService.deleteUser(id);
            logger.info("------ User deleted successfully ------");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Error User Not Exist ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
