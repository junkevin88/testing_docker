package com.binar5.apps.chapter3.lamda.crud;

public class ImlmBarang {

    public static void main(String[] args) {
        ServiceBarang smi = ImlmBarang::simpanImpl ;
        smi.simpan("nama", "123");
    }
    private static void simpanImpl(String nama, String nik) {
        System.out.println("simpan 4");
    }


    private static void simpanImpl(String s) {
        System.out.println("simpan 3");
    }

    public static  void simpanImpl(){
        System.out.println("simpan ");
    }

}
