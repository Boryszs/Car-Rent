package com.Server.controller;

import com.Server.dto.Response.MessageResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   TestController is use to check whether Server is running and check role user work.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 1.0.
 *   @since 2020-12-29.
 */

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    /**
     * This method return text for all user.
     * This method use endpoint /test/all.
     * @return Text Public Content.
     */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    /**
     * This method return text for user and admin.
     * This method use endpoint /test/user.
     * @return Text User Content.
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public MessageResponse userAccess() {
        return new MessageResponse("User Content.");
    }

    /**
     * This method return text for admin.
     * This method use endpoint /test/admin.
     * @return Text Admin Board.
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse adminAccess() {
        return new MessageResponse("Admin Board.");
    }
}
