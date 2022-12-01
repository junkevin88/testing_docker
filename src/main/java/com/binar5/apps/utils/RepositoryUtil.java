package com.binar5.apps.utils;


import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.binar5.apps.entity.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUtil {
    public RepositoryUtil() {
        super();
    }

    public Query createQuery(EntityManager entityManager, String qlString, Pageable page, String contextPath) {
        StringBuffer sb = new StringBuffer(qlString);

        if (page != null) {
            Sort sort = page.getSort();
            if (sort != null && !sort.isEmpty()) {
                sb.append(" ORDER BY ");

                for (Iterator iterator = sort.iterator(); iterator.hasNext();) {
                    Order o = (Order) iterator.next();

                    sb.append(contextPath).append(".");
                    sb.append(o.getProperty()).append(" ");
                    sb.append(o.getDirection().toString()).append(" ");
                }
            }
        }

        Query query = entityManager.createQuery(sb.toString());

        // paging
        if (page != null) {
            query.setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize());
        }
        //kelemahan, ga bisa pagination
        return query;
    }
}
