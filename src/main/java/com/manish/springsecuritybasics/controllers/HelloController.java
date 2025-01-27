package com.manish.springsecuritybasics.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Spring Security Rocks", HttpStatus.OK);
    }

    @GetMapping("/bye")
    public ResponseEntity<String> bye() {
        return new ResponseEntity<>("Bye... See you soon !", HttpStatus.OK);
    }
}
