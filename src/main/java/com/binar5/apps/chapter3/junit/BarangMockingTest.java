package com.binar5.apps.chapter3.junit;

import com.binar5.apps.entity.Barang3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BarangMockingTest {

    @Mock
    public List<Barang3> listBarang3 = new ArrayList<>();

    @Before
    public void init() {

//        //add barang
//        listBarang.add(new Barang(1L, 1000.0));
//        listBarang.add(new Barang(2L, 2599.0));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSaveBarang() {
        try {
            // menambah data req : dummy
            Mockito.when( listBarang3.get(0)).thenReturn(new Barang3(1L,20.0));
            Mockito.when( listBarang3.get(1)).thenReturn(new Barang3(2L,30.0));
            Mockito.when( listBarang3.get(2)).thenReturn(new Barang3(3L,40.0));

            System.out.println("listBarang.get(0).getid ="+ listBarang3.get(0).getId());
            // cara dengan : Objek semua dalam 1 record
            Assert.assertEquals(new Barang3(1L,20.0), listBarang3.get(0));

            // value id saja atau harga nya
            Assert.assertEquals(Optional.of(20.0), Optional.ofNullable(listBarang3.get(0).getHarga()));

            //chek null
            Assert.assertNotNull(listBarang3.get(5));


        } catch (Exception e) {
            System.out.println("eror="+e.getMessage());
        }
    }
}
