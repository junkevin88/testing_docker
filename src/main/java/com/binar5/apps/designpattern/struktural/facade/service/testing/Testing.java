package com.binar5.apps.designpattern.struktural.facade.service.testing;

import com.binar5.apps.designpattern.struktural.facade.service.controller.FacadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Testing {

    @Autowired
    public FacadeService facadeService;

    @Test
    public void testing() {
        facadeService.totalHarga();
    }
}
