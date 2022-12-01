package com.binar5.apps.repository;

import com.binar5.apps.entity.Barang;
import com.binar5.apps.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends PagingAndSortingRepository<Supplier, Long> {
    /*

    selet * from barang;
    1. native query :
      contoh : selet * from barang;

     */

    /*
    2. JPQL : JPA query : yang di slect adalah nama class variable

     */
    @Query(value = "select c from Supplier c WHERE c.id = :idbarang", nativeQuery = false)
    public Supplier getById(@Param("idbarang") Long idbebas);

    @Query(value = "select c from Supplier c ", nativeQuery = false)
    public Page<Supplier> getListData(Pageable pageable);

    @Query("FROM Supplier u WHERE LOWER(u.nama) like LOWER(:nama)")
    public Page<Supplier> findByNamaLike(String nama, Pageable pageable);

    public Page<Supplier> findByHpLike(String hp, Pageable pageable);

    public Page<Supplier> findByNamaLikeAndHpLike(String nama, String  hp, Pageable pageable);


}
