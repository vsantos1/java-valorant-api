package com.vsantos1.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class TestResource {

    @GetMapping(value = "/test")
    public String test() {
        return "Hello World, this endpoint is secured";
    }
}
