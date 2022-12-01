package com.binar5.apps.chapter4.sampleapi.controller;

import com.binar5.apps.chapter4.sampleapi.model.BarangDummy;
import com.binar5.apps.chapter4.sampleapi.model.KaryawanDummy;
import com.binar5.apps.chapter4.sampleapi.service.KaryawanInterfaceDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan/dummy")
public class KaryawanDummyController {

    @Autowired
    public KaryawanInterfaceDummy karyawanInterfaceDummy;


    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<Map> getList() {
        Map map = karyawanInterfaceDummy.list();
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Map> save(   @RequestBody KaryawanDummy objModel) {
        Map obj = karyawanInterfaceDummy.save(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);// response
    }

}
