package com.binar5.apps.bean;

import com.binar5.apps.AppsApplication;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component // saat aplikasi running, beans otomatis dbuat.
@Configuration
public class KaryawanBean {
    private static Logger logger = LoggerFactory.getLogger(AppsApplication.class);

    String nik;

    @Bean("namaBenasYangKitaBerikan") //nama alias bean : secara default ngikutin mehtod,
    public String getNamaSaya() {
        return "Riki Aldi Pari";
    }


    @Bean
    @Primary
    public KaryawanBean karyawan1() {
        logger.info("karyawan 1");
        return new KaryawanBean();
    }

    @Bean
    public KaryawanBean karyawan2() {
        logger.info("karyawan 2");
        return new KaryawanBean();
    }

    @Bean
    @PostConstruct // biasanya digunaka saaat buka koneksi db
    public KaryawanBean karyawan3() {
        logger.info("PostConstruct karyawan 3");// dipangil setiap beans dimulai.
        /*
        akan di apngil setiap method beans
         */
        return new KaryawanBean();
    }

    //
    @Bean
    @PreDestroy
// biasanya digunaka saaat tutup koneksi db
    void karyawan4() {
        logger.info("PreDestroy karyawan 3");// dipangil terakhir kali beans akn di.
        /*
        akan di apngil setiap method beans sebelum di hancurkan, destroy
         */

    }

}
