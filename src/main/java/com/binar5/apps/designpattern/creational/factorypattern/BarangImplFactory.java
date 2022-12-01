package com.binar5.apps.designpattern.creational.factorypattern;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BarangImplFactory extends AbstrakCrud {

    @Override
    public Map save(Object req) {
        return  sukses("BarangImpl sukses save");
    }

    @Override
    public Map update(Object req) {
        return  sukses("BarangImpl sukses update");
    }

    @Override
    public Map delete(Object req) {
        return  sukses("BarangImpl sukses deleted");
    }

    @Override
    public Map list(Object req) {
        return  sukses("BarangImpl sukses list");
    }

    public Map sukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("code", 200);
        map.put("status", "sukses");
        return map;
    }
}
