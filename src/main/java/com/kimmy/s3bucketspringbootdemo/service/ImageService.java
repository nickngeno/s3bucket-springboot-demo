package com.kimmy.s3bucketspringbootdemo.service;

import com.kimmy.s3bucketspringbootdemo.entity.Image;
import com.kimmy.s3bucketspringbootdemo.repository.ImageRepo;
import com.kimmy.s3bucketspringbootdemo.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private S3Client s3Client;

    @Value("${bucket-name}")
    private String bucketName;


    public ApiResponse uploadImageToS3(MultipartFile file) {
        log.info("Uploading image to s3");
        String requestId = UUID.randomUUID().toString();
        String timestamp  = LocalDateTime.now().toString();

        // Check if the file exists
        if (null == file || file.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path: ");
        }

        // Save to S3
        String s3Key = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType(file.getContentType())  // You may need to implement this method
                .build();

        try {
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            if (putObjectResponse.sdkHttpResponse().isSuccessful()) {
                saveImageDetailsInDatabase(s3Key);
                return ApiResponse.success(requestId, 200, "Successfully uploaded the image!",timestamp, s3Key);
            }

        } catch (S3Exception | IOException e ) {
            // Handle exceptions
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return ApiResponse.error(requestId, 400, "Error uploading image to S3!",timestamp, s3Key);
    }

    private String generateFileName(String fileName) {
        return UUID.randomUUID() + "_" + fileName.toLowerCase();
    }

    public void saveImageDetailsInDatabase(String fileName) {
        log.info("Saving image url to db");

        GetUrlRequest getUrlRequest = GetUrlRequest.builder().bucket(bucketName).key(fileName).build();
        String s3ObjectUrl = s3Client.utilities().getUrl(getUrlRequest).toString();

        Image image = new Image();
        image.setName(fileName);
        image.setImageUrl(s3ObjectUrl);
        imageRepo.save(image);
    }
}
