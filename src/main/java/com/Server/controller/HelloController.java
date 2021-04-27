package com.Server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   HelloController is use to check whether swagger library is work.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 2.0.
 *   @since 2020-04-27.
 */

@RestController
public class HelloController {
    /**
     * This method return text Swagger Hello World
     * This method use endpoint /api/javainuse.
     * @return Text Swagger Hello World.
     */
    @GetMapping(value = "/api/javainuse")
    public String sayHello() {
        return "Swagger Hello World";
    }
}
