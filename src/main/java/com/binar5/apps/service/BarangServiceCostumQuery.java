package com.binar5.apps.service;

import com.binar5.apps.repository.BarangRepoCostum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BarangServiceCostumQuery {

    @Autowired
    public BarangRepoCostum barangRepoCostum;

    public Map<String, Object> pageFindAreaByParam(Pageable page, Map<String, Object> request ) {
        return barangRepoCostum.findPageCostumColumn(page, request);
    }
}
