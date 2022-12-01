package com.binar5.apps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
@Where(clause = "deleted_date is null")
public class Supplier extends  AbstractDate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//supplier_id_seq
    private  Long id;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private  String alamat;

    @Column(name = "nama")
    private String nama; //varchar default

    @Column(name = "hp")
    private String hp;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Barang> barang;
}
