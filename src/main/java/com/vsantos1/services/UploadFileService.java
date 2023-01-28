package com.vsantos1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadFileService {

    @Value("${file.upload.api-key}")
    private String API_KEY;

    @Value("${file.upload.cloud-name}")
    private String CLOUD_NAME;
    @Value("${file.upload.api-secret}")
    private String API_SECRET;


    public UploadFileService() {
    }

    public Cloudinary getCloudinary() {
        HashMap<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

    public Map upload(MultipartFile file, String folderName) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map params = ObjectUtils.asMap(
                    "public_id", folderName + "/" + UUID.randomUUID(),
                    "overwrite", false,
                    "resource_type", "auto"
            );
            return getCloudinary().uploader().upload(uploadedFile, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
