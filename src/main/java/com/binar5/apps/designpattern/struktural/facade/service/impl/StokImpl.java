package com.binar5.apps.designpattern.struktural.facade.service.impl;

import com.binar5.apps.designpattern.struktural.facade.service.StokService;
import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class StokImpl implements StokService {

    @Override
    public Map add(Barang request) {
        return sukses(request);
    }

    @Override
    public Boolean chekStok(Barang request,Long qtyOrder) {
         if(qtyOrder<=request.getHarga()){
             return true;
         }
        return false;
    }
    public Map sukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("code", 200);
        map.put("status", "sukses");
        return map;
    }
}
