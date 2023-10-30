package com.greethy.nutrition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class uses for testing API on service discovery
 *
 * @author ThanhKien
 * */
@RestController
public class TestApiController {

    @GetMapping
    public String hello(){
        return "This is nutrition service";
    }

}
