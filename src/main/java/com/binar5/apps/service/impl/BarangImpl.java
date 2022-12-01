package com.binar5.apps.service.impl;

import com.binar5.apps.entity.Barang;
import com.binar5.apps.entity.Supplier;
import com.binar5.apps.repository.BarangRepo;
import com.binar5.apps.repository.SupplierRepo;
import com.binar5.apps.service.BarangService;
import com.binar5.apps.utils.Config;
import com.binar5.apps.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BarangImpl implements BarangService {

    @Autowired
    public BarangRepo barangRepo;

    @Autowired
    public Response response;

    @Autowired
    public SupplierRepo supplierRepo;

    private static Logger logger = LoggerFactory.getLogger(BarangImpl.class);


    @Override
    public Map save(Barang obj) {
        try {
            // not null
            if(response.isRequired(obj.getNama())){
                return response.error("Nama wajib diisi.", Config.ERROR_403);
            }

            if(response.isRequired(obj.getSupplier())){
                return response.error("Supplier Id wajib diisi.", Config.ERROR_403);
            }

            if(response.isRequired(obj.getSupplier().getId())){
                return response.error("Supplier Id wajib diisi.", Config.ERROR_403);
            }

            //wajib : jika berelasi ke tabel
            Supplier chekSupplier = supplierRepo.getById(obj.getSupplier().getId());
            if(response.isRequired(chekSupplier)){
                return response.error("Supplier Id tidak ditemukan.", Config.ERROR_403);
            }
            obj.setSupplier(chekSupplier);

            return response.sukses(barangRepo.save(obj));
        }catch (Exception e){
            logger.error("eror save: {}"+e.getMessage());
            return response.error(e.getMessage(), Config.ERROR_500);
        }

    }

    @Override
    public Map update(Barang obj) {
        return null;
    }

    @Override
    public Map delete(Long obj) {
        return null;
    }

    @Override
    public Map getById(Long obj) {
        return null;
    }
}
