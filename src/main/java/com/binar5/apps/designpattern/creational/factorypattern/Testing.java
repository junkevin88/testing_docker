package com.binar5.apps.designpattern.creational.factorypattern;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Testing {

    @Test
    public void testBarangImpl(){
        AbstrakCrud abs = new BarangImplFactory();
        System.out.println(abs.save(""));
        System.out.println(abs.update(""));
    }

    @Test
    public void testSuppImpl(){
        AbstrakCrud abs = new SupplierImplFactory();
        System.out.println(abs.save(""));
        System.out.println(abs.update(""));
    }
}
