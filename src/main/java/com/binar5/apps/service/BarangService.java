package com.binar5.apps.service;

import com.binar5.apps.entity.Barang;

import java.util.Map;

public interface BarangService {
    public Map save(Barang obj);
    public Map update(Barang obj);
    public Map delete(Long obj);
    public Map getById(Long obj);
}
