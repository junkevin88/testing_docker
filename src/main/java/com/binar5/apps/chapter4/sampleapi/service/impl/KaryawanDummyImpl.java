package com.binar5.apps.chapter4.sampleapi.service.impl;

import com.binar5.apps.chapter4.sampleapi.model.KaryawanDummy;
import com.binar5.apps.chapter4.sampleapi.service.KaryawanInterfaceDummy;
import com.binar5.apps.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service //wajib
public class KaryawanDummyImpl implements KaryawanInterfaceDummy {
    @Autowired //wajib : DI
    public Response response;

    static List<KaryawanDummy> list = new ArrayList<>();

    @Override
    public Map save(KaryawanDummy obj) {
        list.add(obj); //digunakan untuk menambahkan
        return response.sukses(obj);
    }

    @Override
    public Map list() {
        return response.sukses(list);
    }

    @Override
    public Map update(KaryawanDummy obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
