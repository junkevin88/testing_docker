package com.binar5.apps.chapter4.sampleapi.service;

import com.binar5.apps.chapter4.sampleapi.model.BarangDummy;

import java.util.List;
import java.util.Map;

public interface BarangDummyService {
    public Map save(BarangDummy barangDummy);

    public List<BarangDummy> list();

    public Map update(BarangDummy barangDummy);

    public void delete(Long id);

}
