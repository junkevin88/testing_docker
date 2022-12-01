package com.binar5.apps.designpattern.struktural.facade.service;

import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

public interface StokService {
    public Map add(Barang request);
    public Boolean chekStok(Barang request, Long qtyOrder);
}
