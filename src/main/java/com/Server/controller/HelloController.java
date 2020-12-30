package com.Server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *   HelloController is use to check whether swagger library is work.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 1.0.
 *   @since 2020-12-29.
 */

@RestController
public class HelloController {
    /**
     * This method return text Swagger Hello World
     * This method use endpoint /api/javainuse.
     * @return Text Swagger Hello World.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/javainuse")
    public String sayHello() {
        return "Swagger Hello World";
    }
}