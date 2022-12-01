package com.binar5.apps.controller.rest;

import com.binar5.apps.controller.web.IndexController;
import com.binar5.apps.entity.Barang;
import com.binar5.apps.entity.Supplier;
import com.binar5.apps.repository.BarangRepo;
import com.binar5.apps.repository.SupplierRepo;
import com.binar5.apps.service.BarangService;
import com.binar5.apps.service.BarangServiceCostumQuery;
import com.binar5.apps.service.SupplierService;
import com.binar5.apps.utils.Response;
import com.binar5.apps.utils.SimpleStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/binar/barang")
public class BarangController {
    private Logger logger = LoggerFactory.getLogger(BarangController.class);
    @Autowired
    public Response response;

    @Autowired
    public BarangRepo barangRepo;

    @Autowired
    public BarangServiceCostumQuery barangServiceCostumQuery;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public BarangService barangService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Barang barang) {
        return new ResponseEntity<Map>(barangService.save(barang), HttpStatus.OK);
    }

    //    @PutMapping(value = {"/update", "/update/"})
//    public ResponseEntity<Map> update(@RequestBody Supplier supplier) {
//        return new ResponseEntity<Map>(supplierService.update(supplier), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = {"/delete", "/delete/"})
//    public ResponseEntity<Map> delete(@RequestBody Supplier supplier) {
//        return new ResponseEntity<Map>(supplierService.delete(supplier), HttpStatus.OK);
//    }
//
    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long idSupplier) {
        return new ResponseEntity<Map>(response.sukses(barangRepo.getbyID(idSupplier)), HttpStatus.OK);
    }
//
//    @GetMapping("/list")
//    public ResponseEntity<Map> listSupplier(
//            @RequestParam() Integer page,
//            @RequestParam(required = true) Integer size,
//            @RequestParam(required = false) String orderby,
//            @RequestParam(required = false) String ordertype) {
//        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
//        Page<Supplier> list = null;
//        list = supplierRepo.getListData(show_data);
//        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
//    }

    @GetMapping("/list/costum")
    public ResponseEntity<?> findDataCosutum(  @RequestBody Map<String, Object> request ) {
        try {
            if(  StringUtils.isEmpty(request.get("page").toString()) ){
                return new ResponseEntity<Map>(response.sukses("page wajib diisi"), HttpStatus.BAD_REQUEST);
            }
            if(  StringUtils.isEmpty(request.get("size").toString()) ){
                return new ResponseEntity<Map>(response.sukses("size wajib diisi"), HttpStatus.BAD_REQUEST);
            }
            Integer page  = Integer.valueOf(request.get("page").toString());
            Integer size  = Integer.valueOf(request.get("size").toString());
            Pageable show_data = simpleStringUtils.getShort(null, null, page, size);
            Map<String, Object> findByParam = barangServiceCostumQuery.pageFindAreaByParam(show_data, request);
            return ResponseEntity.ok(findByParam);
        } catch (Exception e) {
            logger.error("error get data {}", e);
        }
        return ResponseEntity.noContent().build();
    }
}
