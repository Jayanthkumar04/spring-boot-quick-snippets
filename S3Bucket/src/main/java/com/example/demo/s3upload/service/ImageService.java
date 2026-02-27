package com.example.demo.s3upload.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.s3upload.entity.Image;
import com.example.demo.s3upload.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
    private S3Service s3Service;
    @Autowired
	private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {

        // Upload to S3
        String imageUrl = s3Service.uploadFile(file);

        // Save URL in DB
        Image image = new Image();
        image.setImageName(file.getOriginalFilename());
        image.setImageUrl(imageUrl);

        return imageRepository.save(image);
    }
}