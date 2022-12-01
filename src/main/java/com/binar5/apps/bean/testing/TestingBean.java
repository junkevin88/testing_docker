package com.binar5.apps.bean.testing;

import com.binar5.apps.bean.KaryawanBean;
import com.binar5.apps.bean.Message;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootTest
public class TestingBean {

    private static Logger logger = LoggerFactory.getLogger(TestingBean.class);


    @Test
    public void testKaryawan() {
        //
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(KaryawanBean.class);
        KaryawanBean kar1 = applicationContext.getBean("karyawan1", KaryawanBean.class);
        KaryawanBean kar1_2 = applicationContext.getBean("karyawan1", KaryawanBean.class);// menandahkan walaupun di call 2 kali tapi objeknya sama

//        Assertions.assertEquals(kar1, kar1_2);// menandahkan walaupun di call 2 kali tapi objeknya sama
        KaryawanBean kar3 = applicationContext.getBean("karyawan3", KaryawanBean.class);
        KaryawanBean kar4 = applicationContext.getBean("karyawan4", KaryawanBean.class);

        KaryawanBean kar1_lagi = applicationContext.getBean("karyawan1", KaryawanBean.class);
        System.out.println("");
//        Assertions.assertEquals(kar1, kar1_lagi);// menandahkan walaupun di call 2 kali tapi objeknya sama
    }

    @Test
    public void testingMessage() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Message.class);
        Message message1 = applicationContext.getBean("message1", Message.class);

    }


    @Autowired
    @Value("${apps.name}")
    String APLIKASI_SAYA;

    @Autowired
    @Value("${BASEURL}")
    String URL_STATIC;


    @Autowired
    public Environment env;

    public static String getenv(String name) {
        return name;
    }

    @Test
    public void testingApplicationProperties() {

//        System.out.println(System.getenv("BASEURL") = ");
        System.out.println(System.getenv("BASEURL"));

        // gets the value of the specified environment variable "TEMP"
//        System.out.print("System.getenv("TEMP") = ");
        System.out.println(System.getenv("BASEURL"));

        // gets the value of the specified environment variable "USERNAME"
//        System.out.print("System.getenv("USERNAME") = ");
        System.out.println(System.getenv("BASEURL"));
        System.out.println(System.getenv("BASEURL"));
//        String ad= env.getProperty("BASEURL");
//        System.out.println(ad);
//        logger.info("APLIKASI_SAYA=, {}",ad);
//        logger.info("URL_STATIC=, {}",URL_STATIC);
    }

}
