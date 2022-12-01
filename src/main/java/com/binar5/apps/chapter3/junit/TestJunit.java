package com.binar5.apps.chapter3.junit;

import com.binar5.apps.entity.Barang3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

public class TestJunit {

    public  List<Barang3> listBarang3;

    @Before
    public void init() {
        listBarang3 = new ArrayList<>();
        //add barang
        listBarang3.add(new Barang3(1L, 1000.0));
        listBarang3.add(new Barang3(2L, 2599.0));
    }

    @Test
    public void doSaveBarang() {
        try {
            Double jumlahHarga = 0.0;
            for (Barang3 data : listBarang3) {
                jumlahHarga = jumlahHarga + data.getHarga();
            }
            System.out.println(jumlahHarga);
            Assert.assertEquals(3599.0, jumlahHarga, 0.0);
            Assert.assertEquals(4000.0, jumlahHarga, 0.0);
        } catch (Exception e) {

        }
    }

    @Test
    // test Case yang negatif
    public void testException() {
        //tidak dapat input scanner
//        Scanner ch = new Scanner(System.in);
//        String data = ch.nextLine();
//        System.out.println(data);
        Exception e = Assertions.assertThrows(InvalidReqExcep.class, () -> new Barang3(null, null));
//        Assert.assertEquals("Req harus diisi", e.getMessage());
        Integer a = null;
        Integer b = 10;
        Assert.assertEquals(20, ((a == null ? 5 : a) + b));
    }


//    @Test
//    public  void saatMakan(){
//        System.out.println("makan durian");//2
//    }
//
//    @BeforeAll
//    static  void sebelumMakan4(){
//        System.out.println("berdoa4"); //1  print ini duluan
//    }
//
//    @Before
//    public  void sebelumMakan2(){
//        System.out.println("berdoa2"); //1  print ini duluan
//    }
//
//    @Before
//    public  void sebelumMakan3(){
//        System.out.println("berdoa3"); //1  print ini duluan
//    }
//
//    @Before
//    public  void sebelumMakan(){
//        System.out.println("berdoa"); //1  print ini duluan
//    }
//
//
//
//    @After
//    public  void setelahMakan(){
//        System.out.println("bersyukur");//3
//    }


    @Test
    @RepeatedTest(5)
    @DisplayName("tampil di method")
    public void test1() {
        System.out.println("test1");
    }


    @Test
    public void test2() {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println(c);
        Assert.assertEquals(3, c);
    }

    // contoh salah, tidak boleh emngunakan mehtod return, tapi harus void
//    @Test
//    public String  methodSalah(){
//        System.out.println("cetak");
//        return null;
//    }


    // contoh salah, tidak diperbelehkan method dengan parameter
//    @Test
//    public void   test2(String abc){
//        System.out.println("test2");
//    }


//    @Before
//    public void methodBefore() {
//        System.out.println("before");
//    }
//
//    @After
//    public void methodAfter() {
//        System.out.println("after");
//    }

}
