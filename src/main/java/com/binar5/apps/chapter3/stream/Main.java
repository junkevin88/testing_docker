package com.binar5.apps.chapter3.stream;

import com.binar5.apps.entity.Barang2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //contoh 1
        Stream<String> stream1 = Stream.of("123A", "12", "234");

        Stream<String> streamArray = Arrays.asList("123", "12", "234").stream();

        // use 1
        streamArray.forEach((n) -> {
            System.out.println(n);
        });

//        // use 2 dan sterus nya : akan eror, karena stream hanya bisa digunakan sekali pakai
//        streamArray.forEach((n) -> {
//            System.out.println(n);
//        });

        stream1.forEach((n) -> {
            System.out.println(n);
        });

        //contoh 2
        List<Barang2> list = new ArrayList<>();
        Barang2 a = new Barang2();
        a.setHarga(20.0);
        list.add(a);

        Barang2 a2 = new Barang2();
        a2.setHarga(30.0);
        list.add(a2);

        Barang2 a3 = new Barang2();
        a3.setHarga(50.0);
        list.add(a3);
//filter by harga
        List<Barang2> filter = list.stream().
                sorted(Comparator.comparingDouble(Barang2 :: getHarga).reversed()). // urutkan berdasarkan harga
                filter(p -> p.getHarga() >= 10).collect(Collectors.toList());
        filter.forEach((n) -> {
            System.out.println("barang tampil ="+n.getHarga());
        });

        //contoh 3
        List<String> nama = Arrays.asList("dodi", "dini", "anton");

        List<String> sortNama = nama.stream().sorted().collect(Collectors.toList());

        // short reverse
        List<String> sortNamaReverse = nama.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());



        // short Objek barang reversed : urut menurun..
        List<Barang2> filterBarang = list.stream().sorted(Comparator.comparingDouble(Barang2 :: getHarga).reversed()).collect(Collectors.toList());
        filterBarang.forEach((n) -> {
            System.out.println("filterBarang="+n.getHarga());
        });

    }
}
