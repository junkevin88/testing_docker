package com.binar5.apps.utils.jasper;


import net.sf.jasperreports.engine.JRException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Main {
    @Autowired
    public  ReportService1 reportService1;

    @Test
    public  void tets() throws SQLException {
        Map<String, Object> parameters33 = new HashMap<>();
        parameters33.put("logo", "C:\\Users\\USER\\Downloads\\hard-01.jpg");
        parameters33.put("idSupplier", 1L);
        parameters33.put("pathReport", "C:\\Users\\USER\\JaspersoftWorkspace\\binar\\");
//        String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\baranglist.jrxml";
//                String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\Supplier2.jrxml";
        String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\Supplier_include_barang.jrxml";//bisa dengan gambar, namun tidak bisa load sub report
        reportService1.generate_pdf(parameters33,pathUrl);
        reportService1.generateHtml(parameters33,pathUrl);
        reportService1.generate_excel(parameters33,pathUrl);
        reportService1.generateCSV(parameters33,pathUrl);
        reportService1.generateDocx(parameters33,pathUrl);
    }

    @Test
    public  void gabungManyReportPDF() throws SQLException {
        Map<String, Object> parameters33 = new HashMap<>();
        parameters33.put("logo", "C:\\Users\\USER\\Downloads\\hard-01.jpg");
        parameters33.put("idSupplier", 1L);
        parameters33.put("pathReport", "C:\\Users\\USER\\JaspersoftWorkspace\\binar\\");
//        String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\baranglist.jrxml";
//                String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\Supplier2.jrxml";
        String pathUrl = "E:\\binar\\binar synergy batCh 5\\Project\\apps\\report\\Supplier_include_barang.jrxml";//bisa dengan gambar, namun tidak bisa load sub report
        reportService1.exportMerge5PDF(pathUrl,parameters33);
    }
}
