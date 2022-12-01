package com.binar5.apps.controller.rest;

import com.binar5.apps.entity.Supplier;
import com.binar5.apps.repository.SupplierRepo;
import com.binar5.apps.service.SupplierService;
import com.binar5.apps.utils.Response;
import com.binar5.apps.utils.SimpleStringUtils;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
//@Hidden
@RestController
@RequestMapping("/v1/binar/supplier")
public class SupplierController {

    @Autowired
    public Response response;

    @Autowired
    public SupplierRepo supplierRepo;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public SupplierService supplierService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Supplier supplier) {
        return new ResponseEntity<Map>(supplierService.save(supplier), HttpStatus.OK);
    }

    @PutMapping(value = {"/update", "/update/"})
    public ResponseEntity<Map> update(@RequestBody Supplier supplier) {
        return new ResponseEntity<Map>(supplierService.update(supplier), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete", "/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Supplier supplier) {
        return new ResponseEntity<Map>(supplierService.delete(supplier), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long idSupplier) {
        return new ResponseEntity<Map>(supplierService.getById(idSupplier), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) String hp,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Supplier> list = null;

        if(nama != null && !nama.isEmpty() && hp != null && !hp.isEmpty() ){
            list = supplierRepo.findByNamaLikeAndHpLike("%"+nama+"%","%"+hp+"%",show_data);
        }else if(hp != null && !hp.isEmpty()){
            list = supplierRepo.findByHpLike("%"+hp+"%",show_data);
        }else if(nama != null && !nama.isEmpty()){
            list = supplierRepo.findByNamaLike("%"+nama+"%",show_data);
        }else {
            // nampilkan semuanya
            list = supplierRepo.getListData(show_data);
        }
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
