package com.sda.travelagency.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloPawel")
public class HelloController {

    @GetMapping
    String getHello() {
        return "hello pawel";
    }
}
