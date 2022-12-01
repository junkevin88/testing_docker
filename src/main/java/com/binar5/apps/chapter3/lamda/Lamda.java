package com.binar5.apps.chapter3.lamda;

import com.binar5.apps.entity.Barang3;
import com.binar5.apps.entity.Barang2;
import com.binar5.apps.entity.DetailBarang;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Lamda {
    public static void main(String[] args) {
//        testDenganLamda();
//        testDenganLamda();
//        lamdaArray();
//        chekNullPointer(null);
//        chekObjeknull();

//        typeLamda();
        lamdaArray();
    }

    //contoh 1
    public static void testTampaLamda() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        r.run();
    }

    public static void testDenganLamda() {
        Runnable r = () -> {//jika banyak,
            System.out.println("hello lamda");
        };
        r.run();
//boleh ga pake kurung kurawal, dengan catatan hanya 1 baris,
        Runnable r2 = () -> System.out.println("hello r2 ");
        r2.run();


    }

    //contoh arrray
    public static void lamdaArray() {
        ArrayList<Integer> nomor = new ArrayList<Integer>();
        nomor.add(5);
        nomor.add(9);
        nomor.add(8);
        nomor.add(1);

        ArrayList<Integer> nomor2 = new ArrayList<Integer>();
        nomor2.add(5);
        nomor2.add(9);
        nomor2.add(8);
        nomor2.add(1);
        // mengunakan lamda
        nomor.forEach((n) -> {
            System.out.println(n);
            nomor2.forEach((k) -> {
                System.out.println("looping ke 2="+k);
            });
        });

        // tidak mengunakan lamda
        for (int i = 0; i < nomor.size(); i++) {
            System.out.println("for =" + nomor.get(i));
        }

        // kita mengunakan objek
        List<Barang3> data = new ArrayList<>();
        data.add(new Barang3(1L, 20.0));
        data.forEach((obj) -> {
            Barang3 tampungObjek = (Barang3) obj;
            System.out.println(tampungObjek);
        } );
        System.out.println("sebelum size="+data.size());
        // data dalam for,, if id > 3, datanya dihapus
        data.removeIf(data2 -> data2.getId()>=1);
        System.out.println("size="+data.size());
    }


    public static int chekNullPointer(Integer data3) {
        try {
            //valivadasi
            if (data3 == null) {
                System.out.println("masuk return valoue 1");
                return 1;
            }

            int tampungData = data3;
        } catch (Exception e) {
            System.out.println("Eror =:" + e.getMessage());
            System.out.println("Eror2 =:" + e);
        }
        return 0;
    }

    public static void chekObjeknull() {
        DetailBarang db = new DetailBarang();
        db.setId(3L);
        db.setDeksripsi(null);
        Barang2 barang = new Barang2();
        barang.setId(1L);
        barang.setHarga(20.0);
//        barang.setDetailBarang(db);
        barang.setDetailOptional(Optional.of(db));

        System.out.println(barang.getId());
        if (barang.getDetailBarang() == null) {
            System.out.println("dertail barang null");
        } else {
            System.out.println(barang.getDetailBarang().getId());
        }

        //Ambil deksripsi di objek Detail barang
        Optional<Barang2> sc = Optional.of(barang);
//        sc.flatMap(Barang2 :: getDetailOptional).
//                map(DetailBarang:: getDeksripsi).
//                orElse("tidak ada");

//       String getDeskripsiSaja =   sc.flatMap(Barang2 :: getDetailOptional).
//                map(DetailBarang:: getDeksripsi).
//                orElse("tidak ada");
//        System.out.println("print data deskripsi :"+getDeskripsiSaja);


//      Optional<DetailBarang> detailSaja =   sc.flatMap(Barang2 :: getDetailOptional); ///.  // ambil objek class
////                map(DetailBarang:: getDeksripsi). //ambil atribut di class tersebut
////                orElse("tidak ada");
//        System.out.println("print data detailSaja :"+detailSaja);

        if (sc.flatMap(Barang2::getDetailOptional).isPresent()) {
            Optional<DetailBarang> detailSaja = sc.flatMap(Barang2::getDetailOptional); ///.  // ambil objek class
//                map(DetailBarang:: getDeksripsi). //ambil atribut di class tersebut
//                orElse("tidak ada");
            System.out.println("print data detailSaja :" + detailSaja);
        } else {
            System.out.println("data null");
        }

    }

    public static  void typeLamda( ){
        Consumer<String> chek = t -> System.out.println("data dari t");
        System.out.println("chek="+chek);

//       Consumer<String, String> chek2 = (x, y) ->   System.out.println("data dari t");

    }

}
