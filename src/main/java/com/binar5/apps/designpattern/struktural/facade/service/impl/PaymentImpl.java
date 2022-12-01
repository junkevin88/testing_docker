package com.binar5.apps.designpattern.struktural.facade.service.impl;

import com.binar5.apps.designpattern.struktural.facade.service.OrderService;
import com.binar5.apps.designpattern.struktural.facade.service.PaymentService;
import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentImpl implements PaymentService {

    public Map sukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("code", 200);
        map.put("status", "sukses");
        return map;
    }

    @Override
    public Map payment(Barang request, Long qty) {
        return sukses("Pembayaran berhasil");
    }
}
