package com.sda.travelagency.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerWojtek {

    @GetMapping("/hellowojtek")
    public String hello(){
        return "Hi :)";
    }
}
