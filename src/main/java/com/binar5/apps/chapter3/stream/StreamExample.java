package com.binar5.apps.chapter3.stream;

import com.binar5.apps.entity.Barang2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        exampleStreamString();
//        contohStreamObjek();
//        gabunganArray();
//        filterBarang();
    }

    public static void exampleStreamString() {
        Stream<String> nama = Stream.of("nama", "budi", "alif");

        Stream<String> namaArray = Arrays.asList("dono", "kasino", "indro", "indro").stream().sorted(Comparator.reverseOrder());

        nama.forEach((n) -> {
            System.out.println("nama =" + n);

        });
        System.out.println("========");

        // ekseskusi ke 1 : sukses
        for (Object data : namaArray.toArray()) {
            System.out.println("dadta =" + data);
//            if (data.equals("kasino")) {
//                break;
//            }
        }
        //Noted : tidak bisa digunakan lagi,karena sekali pakai, sudah digunakan oleh for diatas.
        //// ekseskusi ke 2 : Eror : terminal
        namaArray.forEach((n) -> {
            if (n.equals("kasino")) {
                return;
            }
            System.out.println("namaArray =" + n);

        });
    }

    public static void contohStreamObjek() {
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

        //tampung data list ke Stream
        Stream<Barang2> data = list.stream().sorted(Comparator.comparingDouble(Barang2::getHarga).reversed());
        data.forEach((n) -> {
            System.out.println("harga data= " + n.getHarga());
        });
        /*
        karyawan : id, nama, nik

1. buatlah stream dengan list karyawan :/
2. cetak : foreach
3. nanti saja : sorting berdasrakan nama karywan reversed
         */
    }

    public static void gabunganArray(){
        List<String> nama1 = Arrays.asList("budi","dono");
        List<String> nama2 = Arrays.asList("dhea","nadhira","ani");

        //joinkn jadi satu array
        List<List<String>> join = Arrays.asList(nama1,nama2);

        // manipulasi data dnegna stream
        List<String> joinManipulasi = join.stream().flatMap(po -> po.stream()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("data ="+joinManipulasi);
    }

    public  static  void filterBarang(){List<Barang2> list = new ArrayList<>();
        Barang2 a = new Barang2();
        a.setHarga(20.0);
        a.setId(1L);
        list.add(a);

        Barang2 a2 = new Barang2();
        a2.setHarga(30.0);
        a2.setId(2L);
        list.add(a2);

        Barang2 a3 = new Barang2();
        a3.setHarga(50.0);
        a3.setId(3L);
        list.add(a3);

        Stream<Barang2> data = list.stream().sorted(Comparator.comparingDouble(Barang2::getHarga).reversed())
                .filter(p -> p.getHarga() > 60 )
                .filter(p2 -> p2.getId() >2 && p2.getId() <2);
        data.forEach((n) -> {
            System.out.println("harga data= " + n.getHarga());
        });
    }
}
