package com.vsantos1.resources;

import com.vsantos1.services.UploadFileService;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class FileResource {

    private final UploadFileService uploadFileService;

    public FileResource(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }


    @PostMapping(value = "/upload/file/{folderName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map> uploadPhoto(@PathVariable("folderName") String folderName, @RequestParam("file") MultipartFile file) {

        return ResponseEntity.status(HttpStatus.CREATED).body(uploadFileService.upload(file, folderName));

    }
}
