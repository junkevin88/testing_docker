package com.binar5.apps;

import com.binar5.apps.controller.fileupload.FileStorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


//@EnableSwagger2
@OpenAPIDefinition
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AppsApplication {
    private static Logger logger = LoggerFactory.getLogger(AppsApplication.class);


    @Value("${appsname}")
    static String APLIKASI_SAYA;

    @Value("${BASEURL}")
    @Autowired
    String URL_STATIC;

    public static void main(String[] args) {
        SpringApplication.run(AppsApplication.class, args);
        System.out.println("hello word=" + APLIKASI_SAYA);
        logger.info("APLIKASI_SAYA=" + APLIKASI_SAYA);
    }

    //menampilkan bardasarkan path tertentu saja swagger
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.binar5.apps.controller.rest"))
//                .paths(PathSelectors.ant("/api/v1/binar/supplier/**"))
//                .build();
//    }

}
