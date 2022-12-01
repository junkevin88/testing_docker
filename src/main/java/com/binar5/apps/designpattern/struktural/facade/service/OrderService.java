package com.binar5.apps.designpattern.struktural.facade.service;

import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;

import java.util.Map;

public interface OrderService {
    public Map orderBarangTotal(Barang request, Long qtyOrder);
}
