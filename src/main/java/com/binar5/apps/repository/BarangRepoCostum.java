package com.binar5.apps.repository;

import com.binar5.apps.utils.RepositoryUtil;
import com.binar5.apps.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BarangRepoCostum {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public RepositoryUtil repoUtil;

    @Autowired
    public Response response;
    public Map<String, Object> findPageCostumColumn(Pageable page, Map<String, Object> request  ) {
//        Map<String, Object> result = new HashMap<>();
        String contextPath = "o";

        String qlString = "select  o from Barang o "
                + "left join fetch o.supplier "
//                + "left join fetch o.status s "
//                + "left join fetch o.user u "
//				+ " where s.code != 'deleted'";
                + " where 1=1";

        if (StringUtils.isNotEmpty(request.get("nama").toString())) {
            String clauseName = " and o.nama like :nama ";
            qlString += clauseName;

        }

        if (StringUtils.isNotEmpty(request.get("satuan").toString())) {
            String clauseSatuan = " and o.satuan like :satuan ";
            qlString += clauseSatuan;

        }

        if (StringUtils.isNotEmpty(request.get("supplier").toString())) {
            Map namaSupplier = (Map) request.get("supplier");
            if (StringUtils.isNotEmpty(namaSupplier.get("nama").toString())) {
                String clauseNamaSupplier = " and o.supplier.nama like :namaSupplier ";
                qlString += clauseNamaSupplier;
            }
        }

//        qlString += " order by o.id desc";

        javax.persistence.Query query = repoUtil.createQuery(entityManager, qlString, page, contextPath);


        if (StringUtils.isNotEmpty(request.get("nama").toString())) {
            String paramName = "%" + request.get("nama").toString().trim() + "%";
            query.setParameter("nama", paramName);

        }
        if (StringUtils.isNotEmpty(request.get("satuan").toString())) {
            String paramSatuan = "%" + request.get("satuan").toString().trim() + "%";
            query.setParameter("satuan", paramSatuan);
        }

        if (StringUtils.isNotEmpty(request.get("supplier").toString())) {
            Map namaSupplier = (Map) request.get("supplier");
            if (StringUtils.isNotEmpty(namaSupplier.get("nama").toString())) {
                String paramNamaSupplier = "%" + namaSupplier.get("nama").toString().trim() + "%";
                query.setParameter("namaSupplier", paramNamaSupplier);
            }

        }

        return response.sukses(query.getResultList());
    }

}
