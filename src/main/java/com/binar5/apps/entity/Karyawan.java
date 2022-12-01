package com.binar5.apps.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Karyawan {
    String nama;

    public Karyawan(String nama) {
        this.nama = nama;
    }
}
