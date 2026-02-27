package com.example.demo.s3upload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.s3upload.entity.Image;
import com.example.demo.s3upload.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file)
            throws IOException {

        Image savedImage = imageService.uploadImage(file);
        return ResponseEntity.ok(savedImage);
    }
}