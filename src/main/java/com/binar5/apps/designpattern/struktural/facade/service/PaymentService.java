package com.binar5.apps.designpattern.struktural.facade.service;

import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;

import java.util.Map;

public interface PaymentService {
    public Map payment(Barang request, Long qty);
}
