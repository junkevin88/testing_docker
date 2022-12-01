package com.binar5.apps.bean;

import com.binar5.apps.bean.login.model.Login;
import com.binar5.apps.bean.login.service.ServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

// untuk malakukan  runing di kelas ini
@SpringBootApplication
public class SpringGetApplicationContextApplication implements CommandLineRunner {

    @Autowired // DI : dependency injektion : guna untuk melakukan injeksi terhadap class: supaya spring mspring boot dapat mengenalnya
    ApplicationContextProvider applicationContextProvider;

    @Autowired // DI bagian IOC inversion of control
    Message messageDI;

    @Autowired(required = true)
    public KaryawanBean karyawanBean;

    @Autowired//DI
    public ServiceLogin serviceLogin;


    public static void main(String[] args) {
        SpringApplication.run(SpringGetApplicationContextApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        /*
        noted : class Configuration harus didalam @SpringBootApplication
        jika tidak , maka beans tidak di di buat.
        karena kita running di dalam pakacge bean.
         */
// memangil beans

//        ConfigExample configExample = applicationContextProvider.getApplicationContext().getBean(ConfigExample.class);
//        configExample.nameConfigSaya();
//        configExample.name("");

        Message message = applicationContextProvider.getApplicationContext().getBean(Message.class);
        Message message2 = applicationContextProvider.getApplicationContext().getBean(Message.class);
        System.out.println(message.getMessage());
        System.out.println(message.getNama());
        System.out.println(message2.getMessage());
        message2.methodName();
        message2.methodName("");


        System.out.println("=============");
        System.out.println("nama DI :"+messageDI.getNama());
        System.out.println("message DI :"+messageDI.getMessage());

        System.out.println("=============");
        System.out.println(karyawanBean.getNamaSaya());
        karyawanBean.setNik("138e3rt384");
        System.out.println(karyawanBean.getNik());

        System.out.println("=============");
        Login login = new Login();
        login.setPassword("password");
        login.setUsername("riki aldi");
        Map map= serviceLogin.dologin(login);
        System.out.println(map);


    }
}