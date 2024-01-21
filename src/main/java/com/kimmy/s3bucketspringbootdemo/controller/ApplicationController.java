package com.kimmy.s3bucketspringbootdemo.controller;

import com.kimmy.s3bucketspringbootdemo.service.ImageService;
import com.kimmy.s3bucketspringbootdemo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/")
public class ApplicationController {

    @Autowired
    private ImageService imageService;

    @PostMapping("save-image")
    private ApiResponse saveImageToDb(@RequestParam("file")MultipartFile file){
        return imageService.uploadImageToS3(file);
    }
}
