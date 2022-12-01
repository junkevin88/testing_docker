package com.binar5.apps.utils.zip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ZipClass {
    @GetMapping(value = "/report/download/zip", produces = "application/zip")
    public void zipFiles(HttpServletResponse response) throws IOException {

        // setting headers
        response.setStatus(HttpServletResponse.SC_OK);
        // akan mendownload file dengan nama "filename.zip"
        response.addHeader("Content-Disposition", "attachment; filename=" + "filenameDownload" + ".zip");

        // 1.otomatis dwonload ke client
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        // 2.tulis ke direkctori
        FileOutputStream fos = new FileOutputStream("./cdn/out2.zip");
        ZipOutputStream zipOutputStreamLocalSave = new ZipOutputStream(fos);
//        String prefix = "Temp_";
        String path = "./cdn/";
        String fileName = "out.csv";// file yang diambil : diharcode
        String fileName2 = "out.pdf";// file yang diambil : diharcode
        String fileName3 = "out.xlsx";// file yang diambil : diharcode
        String fileName5 = "hard-01.jpg";// file yang diambil : diharcode
        String fileName6 = "out.docx";// file yang diambil : diharcode
        String name = "zipexport";
        File ktp = FileUtility.commonFindFile(path, fileName);
        File npwp = FileUtility.commonFindFile(path, fileName2);
        File customer = FileUtility.commonFindFile(path,fileName3);
        File signature = FileUtility.commonFindFile(path, fileName5);
        File boKtp = FileUtility.commonFindFile(path, fileName6);
        File boNpwp = FileUtility.commonFindFile(path, fileName);

        Map res = new HashMap<>();
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<!DOCTYPE html><html>  <head> <title> Onboarding</title> </head>");
        htmlStringBuilder.append("<body><h1 style=\"color:black;\"> Data Onboarding  </h1>");
        htmlStringBuilder.append("<pre id=\"dwibd\" style= \"color:black; font-size: 12px;\"></pre>");
        htmlStringBuilder.append("<script> var el_down = document.getElementById(\"dwibd\"); var obj = " + jsonResult + ";");
        htmlStringBuilder.append("el_down.innerHTML = JSON.stringify(obj, undefined, 4);");
        htmlStringBuilder.append("</script> </body> </html>");

        InputStream inputStream = new ByteArrayInputStream(htmlStringBuilder.toString().getBytes(("UTF8")));
        File json = FileUtility.streamTemporaryFile(inputStream, name);
        String[] listType = { "ktp", "npwp", "customer", "signature", "BOKtp", "BONpwp", "jsonData" };
        File[] file = { ktp, npwp, customer, signature, boKtp, boNpwp, json };

        for (int i = 0; i < file.length; i++) {
            if (null != file[i]) {
                zipOutputStream.putNextEntry(new ZipEntry(listType[i] + "_" + file[i].getName()));
                zipOutputStreamLocalSave.putNextEntry(new ZipEntry(listType[i] + "_" + file[i].getName()));

                FileInputStream fileInputStream = new FileInputStream(file[i]);//sekali pakai
                FileInputStream fileInputStream2 = new FileInputStream(file[i]);//seakali pakai

                IOUtils.copy(fileInputStream2, zipOutputStreamLocalSave);
                IOUtils.copy(fileInputStream, zipOutputStream);

 ;
                fileInputStream.close();

                zipOutputStream.closeEntry();
                zipOutputStreamLocalSave.closeEntry();

            }
        }

        // membuat file zip pada local direktori
        zipOutputStreamLocalSave.close();
        zipOutputStream.close();
    }
    /*
    list
    map
    set
     */
}
