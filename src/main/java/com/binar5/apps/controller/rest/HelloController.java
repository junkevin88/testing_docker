package com.binar5.apps.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/rest/")
public class HelloController {

    @GetMapping(value = {"/hello","/hello2/"})
    public ResponseEntity<String> helloWord(){
        return new ResponseEntity<String>("Hello Word response", HttpStatus.OK);
    }
}
