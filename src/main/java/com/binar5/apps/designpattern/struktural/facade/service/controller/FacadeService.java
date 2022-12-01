package com.binar5.apps.designpattern.struktural.facade.service.controller;

import com.binar5.apps.designpattern.struktural.facade.service.OrderService;
import com.binar5.apps.designpattern.struktural.facade.service.PaymentService;
import com.binar5.apps.designpattern.struktural.facade.service.StokService;
import com.binar5.apps.designpattern.struktural.facade.service.model.Barang;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@Component
public class FacadeService {
    @Autowired
    public OrderService orderService;
    @Autowired
    public StokService stokService;
    @Autowired
    public PaymentService paymentService;

    public Map totalHarga() {
     /*
     step 1 : add produk : ServiceStok
     step 2 : chek stok produk sebelum dipesan : qty : ServiceStok
     step 3 : order produk : OrderService
     step 4 : total harga : paymentService
      */
//         step 1 : add produk
        Barang barang = new Barang();
        barang.setId(1L);
        barang.setHarga(100000.0);
        barang.setStok(10);
        Map addBarang = stokService.add(barang);
        System.out.println("step 1 addBarang=" + addBarang);
//        step 2 : chek stok produk sebelum dipesan
        Boolean chekStok = stokService.chekStok(barang, 5L);
        System.out.println("step 2 chekStok=" + chekStok);
//        step 3 : order produk
        if (chekStok) {
            Map order = orderService.orderBarangTotal(barang, 5L);
            System.out.println("step 3 order=" + order);
//        step 4 : total harga ; menghitung
            Map payment = paymentService.payment(barang, 5L);
            System.out.println("step 4 payment=" + payment);
            return payment;
        }

        return null;
    }

}
