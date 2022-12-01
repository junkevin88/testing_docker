package com.binar5.apps.utils.jasper;


import com.binar5.apps.bean.Message;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportService1 {
    private static Logger logger = LoggerFactory.getLogger(ReportService1.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public byte[] generate_pdf(Map<String, Object> parameters, String reportName) throws SQLException {
        try {
            JasperReport report = JasperCompileManager.compileReport(reportName);
            System.out.println("reportreport="+reportName);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport
                    (report,
                            parameters,
                            jdbcTemplate.getDataSource().getConnection());
//            JasperPrint jasperPrint = JasperFillManager
//                    .fillReport
//                            (reportName,
//                                    parameters,
//                                    dataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "./cdn/out.pdf");
            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint) ;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            //close koneksi
            jdbcTemplate.getDataSource().getConnection().close();
        }
    }

    public byte[] generateDocx(Map<String, Object> parameters, String reportName) throws SQLException {
        try {
            JasperReport report = JasperCompileManager.compileReport(reportName);
            System.out.println("reportreport="+reportName);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport
                            (report,
                                    parameters,
                                    jdbcTemplate.getDataSource().getConnection());
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            File exportReportFile = new File("./cdn/out.docx");
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
            exporter.exportReport();
            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint) ;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            //close koneksi
            jdbcTemplate.getDataSource().getConnection().close();
        }
    }


    public byte[] generateHtml(Map<String, Object> parameters, String reportName) {
        try {
            JasperReport report = JasperCompileManager.compileReport(reportName);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,jdbcTemplate.getDataSource().getConnection());
            //perbedaan hanya disini
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "./cdn/out.html");
            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint) ;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity generate_excel(Map<String, Object> parameters, String reportName) throws SQLException {
        Connection connection = null;
        byte[] data = new byte[0];
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            connection = dataSource.getConnection();
            httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportName + ".xlsx" );

            JasperReport report = JasperCompileManager.compileReport(reportName);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,jdbcTemplate.getDataSource().getConnection());

            JRXlsxExporter exporter = new JRXlsxExporter();

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.exportReport();
            data = byteArrayOutputStream.toByteArray();
            OutputStream out = new FileOutputStream("./cdn/out.xlsx");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.close();
                connection = null;
            }
        }
        return new ResponseEntity(data, httpHeaders, HttpStatus.OK );
    }

    public ResponseEntity generateCSV(Map<String, Object> parameters, String reportName) throws SQLException {
        Connection connection = null;
        byte[] data = new byte[0];
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            connection = dataSource.getConnection();
            httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportName + ".csv" );

            JasperReport report = JasperCompileManager.compileReport(reportName);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,jdbcTemplate.getDataSource().getConnection());

            JRXlsxExporter exporter = new JRXlsxExporter();

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.exportReport();
            data = byteArrayOutputStream.toByteArray();
            OutputStream out = new FileOutputStream("./cdn/out.csv");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.close();
                connection = null;
            }
        }
        return new ResponseEntity(data, httpHeaders, HttpStatus.OK );
    }

    public void exportMerge5PDF(String jrxmlOutputTemplate1, Map parameters1){
        try {
            JasperDesign design1 = JRXmlLoader.load(jrxmlOutputTemplate1);
            JasperReport report1 = JasperCompileManager.compileReport(design1);

            JasperPrint print1 = JasperFillManager.fillReport(report1, parameters1, jdbcTemplate.getDataSource().getConnection());

            JasperDesign design2 = JRXmlLoader.load(jrxmlOutputTemplate1);
            JasperReport report2 = JasperCompileManager.compileReport(design2);

//            JasperPrint print2 = JasperFillManager.fillReport(report2, parameters1, new JREmptyDataSource());
            JasperPrint print2 = JasperFillManager.fillReport(report2, parameters1, jdbcTemplate.getDataSource().getConnection());

            JasperDesign design3 = JRXmlLoader.load(jrxmlOutputTemplate1);
            JasperReport report3 = JasperCompileManager.compileReport(design3);

            JasperPrint print3 = JasperFillManager.fillReport(report3, parameters1,  jdbcTemplate.getDataSource().getConnection());

            JasperDesign design4 = JRXmlLoader.load(jrxmlOutputTemplate1);
            JasperReport report4 = JasperCompileManager.compileReport(design4);

            JasperPrint print4 = JasperFillManager.fillReport(report4, parameters1,  jdbcTemplate.getDataSource().getConnection());

            JasperDesign design5 = JRXmlLoader.load(jrxmlOutputTemplate1);
            JasperReport report5 = JasperCompileManager.compileReport(design5);

            JasperPrint print5 = JasperFillManager.fillReport(report5, parameters1,  jdbcTemplate.getDataSource().getConnection());

            List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
            jasperPrintList.add(print1);
            jasperPrintList.add(print2);
            jasperPrintList.add(print3);
            jasperPrintList.add(print4);
            jasperPrintList.add(print5);

//			generateReportList(print1,print2,print3, print4,pdfOutput);
            generateReportListfleksibel(jasperPrintList, "./cdn/allpdf2.pdf");
//			JasperExportManager.exportReportToPdfFile(print, pdfOutput);
        } catch (JRException e) {
            logger.error("error jasper report {}", e);
        } catch (Exception e) {
            logger.error("error other jasper report {}", e);
        }

    }


    public void generateReportListfleksibel(List<JasperPrint> jasperPrintList, String pdfOutput) {
        //throw the JasperPrint Objects in a list

        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            //Add the list as a Parameter
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
            //this will make a bookmark in the exported PDF for each of the reports
            exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
            exporter.setParameter(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, Boolean.TRUE);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(pdfOutput));
//		  exporter.setParameter(JRPdfExporterParameter.INPUT_FILE_NAME, pdfOutput);

            exporter.exportReport();
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
