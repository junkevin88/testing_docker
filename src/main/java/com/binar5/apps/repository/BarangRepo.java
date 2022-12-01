package com.binar5.apps.repository;

import com.binar5.apps.entity.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepo extends PagingAndSortingRepository<Barang, Long> {
    /*

    selet * from barang;
    1. native query :
      contoh : selet * from barang;

     */
    @Query(value = "select c from barang c WHERE c.id = :idbarang", nativeQuery = true)
    public Object getbyID(@Param("idbarang") Long idbebas);

    @Query(value = "select c from Barang c WHERE c.id = :idbarang", nativeQuery = false)
    public Barang getByID(@Param("idbarang") Long idbebas);

    /*
    2. JPQL : JPA query : yang di slect adalah nama class variable

     */
    @Query(value = "select c from Barang c WHERE c.id = :idbarang", nativeQuery = false)
    public Barang getbyIDByJPQL(@Param("idbarang") Long idbebas);

    public Page<Barang> findBySatuan(String satuan, Pageable pageable);

    public Page<Barang> findBySatuanAndHarga(String satuan, int harga,Pageable pageable );

    public Page<Barang> findBySatuanAndHargaOrNama(String satuan, int harga,  String nama,Pageable pageable);

    public Page<Barang> findBySatuanAndHargaOrNamaLike(String satuan, int harga,  String nama,Pageable pageable);//


}
