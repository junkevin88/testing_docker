package com.binar5.apps.bean;

import com.binar5.apps.AppsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//jika sudah ada componant scan : tidak perlu tulis @Bena di method,  karena sudah otomatis dibuat
@Component // bertujuan men scan mencari : supaya spring boot dapat mengenali class , seblum aplikasi ready
//// saat aplikasi running, beans otomatis dbuat.
@Configuration
public class Message {
    private static Logger logger = LoggerFactory.getLogger(Message.class);

    public String getMessage() {
        return "Hello World! == isi bean";
    }

    public String getNama() {
        return "Riki aldi";
    }


    //    @Primary
    public Message methodName() {
        System.out.println("123");
        return new Message();
    }

    public Message methodName(String nama) {
        System.out.println("456");
        return new Message();
    }



    @PostConstruct
    @Bean
    @Primary//jika banyak benas sperti ini dengan construkter yang sama :maka perlu kita tambahkan @Primary
    public  Message  message1(){
        logger.info("PostConstruct dengan message1");
        return  new Message();
    }

    @Bean
    public  Message  message2(){
        logger.info("Bean dengan message2");
        return  new Message();
    }
    // di eksekusi paling akhir: biasanya digunakan untuk close koneksi
    @Bean
    @PreDestroy
    public  Message  message5(){
        logger.info("PreDestroy dengan message5");
        return  new Message();
    }

    @Bean
    public  Message  message3(){
        logger.info("Bean dengan message3");
        return  new Message();
    }





}