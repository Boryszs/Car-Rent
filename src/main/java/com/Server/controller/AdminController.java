package com.Server.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  Admin Controller used to Test whether Server is correct work.
 *  @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *  @version  1.0.
 *  @since 2020-12-29.
 */
@RequestMapping(value = "/admin")
@RestController
@CrossOrigin
@Configuration
@EnableSwagger2
public class AdminController {
    /**
     * This is method is use to check correct work server.
     * This method use endpoint /admin/test
     * @return Text hello where server is working Http.Status 200.
     */
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getString() {
        return "hello";
    }

    /**
     * This is method is use to return about of company to client.
     * This method use endpoint /admin/about
     * @return Text about company to client Http.Status 200.
     */
    @GetMapping(value = "/about", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAbout() {
        return "about company";
    }

}
