package com.Server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/admin")
@RestController
@CrossOrigin
public class AdminController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getString() {
        return "hello";
    }

    //About company
    @GetMapping(value = "/about", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAbout() {
        return "about company";
    }

}
