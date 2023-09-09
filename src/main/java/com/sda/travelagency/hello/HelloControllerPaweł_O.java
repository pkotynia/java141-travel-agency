package com.sda.travelagency.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloPawełO")
public class HelloControllerPaweł_O {

    @GetMapping
    public String helloPawełO() {
        return "Hello Paweł.O";
    }

}
