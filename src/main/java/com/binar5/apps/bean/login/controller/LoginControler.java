package com.binar5.apps.bean.login.controller;


import com.binar5.apps.bean.login.service.ServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//rest api
@RequestMapping("/v1/acv/")
public class LoginControler {


    @Autowired
    public ServiceLogin serviceLogin;

    //aad mehtod

    @Value("${BASEURL}")
    String BASEURL;
    @Value("${appsname}")
    String appsname;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>(BASEURL  + " aplikasi ="+appsname, new HttpHeaders(), HttpStatus.OK);

    }

}
