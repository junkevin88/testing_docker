package com.binar5.apps.bean.login.impl;

import com.binar5.apps.bean.login.model.Login;
import com.binar5.apps.bean.login.service.ServiceLogin;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//perlu menambahkan @Service : logic
@Service // WAJIB : untuk membuat beans // pertama aplikasi dijalankan maka tidak membuat beans, namaun membuat beans ketika
// ada action . di panggil saja.
public class LoginImpl implements ServiceLogin {
    @Override
    public Map dologin(Login req) {
//        validaasi
        //jika username dan pass cocok maka berhasil else gagal
        Map map = new HashMap();
        if(req.getUsername().equals("riki") && req.getPassword().equals("password")){
            map.put("data","berhasil login");
            map.put("status","sukses");
            return map;
        }
        map.put("data","gagal login");
        map.put("status","gagal");
        return map;
    }
}
