package com.binar5.apps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Data
@Entity
@Table(name = "barang")
@Where(clause = "deleted_date is null")
public class Barang  extends  AbstractDate implements Serializable {

    @Id// primary key
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "nama")
    private  String nama;

    @Column(name = "satuan")
    private String satuan; //varchar default

    @Column(name = "stok")
    private Integer stok;

    @Column(name = "harga")
    private Double harga;


//    @JsonIgnore // salah satu diantarnya harus di json ignore: lebih baik jsoningenore yang typenya list
    @ManyToOne(targetEntity =Supplier.class, cascade = CascadeType.ALL)
    @JoinColumn(name="supplier_id", nullable=true, referencedColumnName = "id")
    private Supplier supplier;// FK membuat kolum FK

    @Transient // Objek tidak akan di buatkan kolumn ke tabel database
    private String hargaRupiah;

    public String getHargaRupiah() {
        if(getHarga() !=null){
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
            return "Rp. "+nf.format(harga);
        }
        return "Rp. 0";
    }
}
