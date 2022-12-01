package com.binar5.apps.service.impl;

import com.binar5.apps.bean.testing.TestingBean;
import com.binar5.apps.entity.Supplier;
import com.binar5.apps.repository.SupplierRepo;
import com.binar5.apps.service.SupplierService;
import com.binar5.apps.utils.Config;
import com.binar5.apps.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SupplierImpl implements SupplierService {
    private static Logger logger = LoggerFactory.getLogger(SupplierImpl.class);
    @Autowired
    public SupplierRepo supplierRepo;

    @Autowired
    public Response response;

    @Override
    public Map save(Supplier request) {
        try {
        /*
        1. bisa validasi : atau tidak
        2. do save
         */
//            1. bisa validasi : atau tidak
            if (request.getNama() == null) {
                return  response.error("Nama wajib diisi.", Config.ERROR_401);
            }
            //langsung save  2. do save
            Supplier doSave = supplierRepo.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Supplier request) {
        try {
        /*
        bisa validasi : atau tidak
        wajib melakukan pengekan ke db : ada atau ganya id supplier
         */
            if (request.getId() == null) {
                return  response.error("Id wajib diisi.", Config.ERROR_401);
            }
//            wajib melakukan pengekan ke db : ada atau ganya id supplier
            Supplier chekData = supplierRepo.getById(request.getId());
            if (chekData == null) {
                return  response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getNama() == null) {
                return  response.error("Nama wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setAlamat(request.getAlamat());
            chekData.setHp(request.getHp());
            chekData.setNama(request.getNama());

            //langsung update
            Supplier doSave = supplierRepo.save(chekData);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror update: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map delete(Supplier request) {
        try {
            /*
           1. chek data kedatabase
           2.do delete
             */
            if (request.getId() == null) {
                return   response.error("Id wajib diisi.", Config.ERROR_401);
            }
//            1. chek data kedatabase
            Supplier chekData = supplierRepo.getById(request.getId());
            if (chekData == null) {
                return   response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Supplier saveDeleted= supplierRepo.save(chekData);
            return response.sukses("sukses");
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror delete: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map getById(Long request) {
        try {
           /*
           1. pengecekakn ke database
           2. tampilkan response
            */

            //validasi
            if (request == null) {
                return  response.error("Id wajib diisi.", Config.ERROR_401);
            }
            //1. chek data kedatabase
            Supplier chekData = supplierRepo.getById(request);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            // response
            return response.sukses(chekData);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror getById: " + e.getMessage(), Config.ERROR_500);
        }
    }
}
