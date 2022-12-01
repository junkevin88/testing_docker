package com.binar5.apps.chapter4.sampleapi.controller;

import com.binar5.apps.chapter4.sampleapi.model.BarangDummy;
import com.binar5.apps.chapter4.sampleapi.service.BarangDummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/barang/dummy/")
public class BarangControllerDummy {

    /*
    post : /v1/karyawan/save
    put: /v1/karyawan/put
    get: /v1/karyawan/{id}
    get: /v1/karyawan/list
    delete: /v1/karyawan/delete

    //list data meynimpan :
     */

    @Autowired
    private BarangDummyService servis;

    @GetMapping("test")
    @ResponseBody
    public ResponseEntity<Map> test() {
        Map map = new HashMap();
        map.put("data", "sukses saya");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<List<BarangDummy>> getList() {
        List<BarangDummy> c = servis.list();
        return new ResponseEntity<List<BarangDummy>>(c, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Map> save(
            @RequestBody BarangDummy objModel) {
        Map obj = servis.save(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);// response
    }

    @PutMapping("update")
    public ResponseEntity<Map> update(
            @RequestBody BarangDummy objModel) {
        Map c = servis.update(objModel);
        return new ResponseEntity<Map>(c, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {

        Map map = new HashMap();
        servis.delete(id);
        map.put("data", "sukses delete");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}