package com.binar5.apps.controller.fileupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@EnableCaching
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @Value("${app.uploadto.cdn}")//FILE_SHOW_RUL
    private String UPLOADED_FOLDER;


    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/v1/upload", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMyyyyhhmmss");
        String strDate =file.getName()+file.getSize()+ formatter.format(date);
        String nameFormat= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") );
        if(nameFormat.isEmpty()){
            nameFormat = ".png";
        }
        String fileName = UPLOADED_FOLDER + strDate + nameFormat;


        String fileNameforDOwnload = strDate + nameFormat;
        Path TO = Paths.get(fileName);


//        //validasi hanya boleh PNG
//        if(!file.getContentType().equals("image/png")){
//            return null;// eror
//        }

        try {
            Files.copy(file.getInputStream(), TO); // pengolahan upload disini :
        } catch (Exception e) {
            e.printStackTrace();
            return new UploadFileResponse(fileName, null, file.getContentType(), file.getSize(), e.getMessage());
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/showFile/")
                .path(fileNameforDOwnload)
                .toUriString();

        return new UploadFileResponse(fileNameforDOwnload, fileDownloadUri, file.getContentType(), file.getSize(), "false");
    }

//    public String getPAth(String type) {
//        try {
//            if (type.toLowerCase().equals("product")) {
//                File theDir = new File(UPLOADED_FOLDER_PRODUCT);
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                    return  UPLOADED_FOLDER_PRODUCT;
//                }
//                return UPLOADED_FOLDER;
//            }
//
//        } catch (Exception e) {
//            logger.info("Eror create folder" + e);
//        }
//        return UPLOADED_FOLDER;
//    }

    @GetMapping("v1/showFile/{fileName:.+}")
    public ResponseEntity<Resource> showFile(@PathVariable String fileName, HttpServletRequest request) { // Load file as Resource : step 1 load path lokasi name file
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            // dapatan URL download
//            System.out.println("resource.getFile().getAbsolutePath" + resource.getFile().getAbsolutePath());
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";// type .json
        }
//        System.out.println("filename=2=" + HttpHeaders.CONTENT_DISPOSITION);
//        System.out.println("filename=3=" + resource.getFilename());
//        System.out.println("filename=3=" + resource);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @PostMapping("v1/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        // step 1 : call method uplaod
                        return uploadFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


    private File multipartToFile(MultipartFile upload, String routeName) {
        String base = "";


        logger.info(String.format("Trying upload file: %s", upload.getOriginalFilename()));

        File file = new File(base + upload.getOriginalFilename());

        try {
            logger.info(String.format("Saving uploaded file to: '%s'", file.getAbsolutePath()));
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(upload.getBytes());
            fos.close();
        } catch (IOException e) {
            logger.error(String.format("Error: POST|UPLOAD %s", routeName), e);
        }

        return file;
    }

    private File multipartToFile(MultipartFile upload) {
        return multipartToFile(upload, UPLOADED_FOLDER);
    }


}
