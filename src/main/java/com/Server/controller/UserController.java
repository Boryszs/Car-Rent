package com.Server.controller;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.dto.Response.UserResponse;
import com.Server.exception.WrongDataException;
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
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
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
    private final UserService userService;

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
     *
     * @param userRequest new data user.
     * @return new data user Http.Status 200 or 400.
     * @throws WrongDataException when server catch Error.
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestParam Long id, @Valid @RequestBody UserRequest userRequest) {
        logger.info("------ User edited successfully ------");
        userService.update(userRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Usuwa User.

    /**
     * This method delete user on id.
     * This method use endpoint /user/delete.
     *
     * @param id id user delete.
     * @return Http.Status 200.
     * @throws WrongDataException when user not exist.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        logger.info("------ User deleted successfully ------");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponse> getUser(@RequestParam Long id) {
        logger.info("------ User get successfully ------");
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
