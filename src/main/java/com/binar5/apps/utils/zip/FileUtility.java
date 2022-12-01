package com.binar5.apps.utils.zip;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtility {

    private final static String EXTENTION_FILE = ".";

    public static File commonFindFileOld(String path, String referenceNumber) {
        File f = new File(path);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(referenceNumber);
            }
        });

        return matchingFiles != null && matchingFiles.length > 0 ? matchingFiles[0] : null;
    }

    public static File commonFindFile(String path, String referenceNumber) {
        File file = null;
        try {
            int lastIndexOf = referenceNumber.lastIndexOf(".");
            path = path.endsWith("/") ? path : (path + File.separator);
            if (lastIndexOf == -1) {
                String[] typeFileList = { "jpg", "jpeg", "png", "pdf", "xls", "xlsx", "JPG", "JPEG", "PNG", "PDF", "XLS", "XLSX" };
                for (String typeFile : typeFileList) {
                    file = new File(path + referenceNumber + EXTENTION_FILE + typeFile);
                    if (file.exists()) {
                        return file;
                    }
                }
            } else {
                file = new File(path + referenceNumber);
                if (file.exists()) {
                    return file;
                }
            }
        } catch (Exception e) {
            log.error("cannot get file ", e);
        }
        return null;
    }

    public static File streamTemporaryFile(InputStream in, String refNumber) throws IOException {
        final File tempFile = File.createTempFile(refNumber, ".html");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }

    public static String getFileExtension(File file) {
        String name = file.getAbsolutePath();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf).replace(".", "");
    }

    public static String getFileName(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        return name;
    }

    public static String removeSpace(String text) {
        if (text == null) {
            return Strings.EMPTY;
        }

        return text.replace(" ", "");
    }


}

