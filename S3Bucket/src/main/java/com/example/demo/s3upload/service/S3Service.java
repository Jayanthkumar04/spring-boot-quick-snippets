package com.example.demo.s3upload.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private S3Client s3Client;
	
	
	@Value("${aws.s3.bucket}")
    private String bucketName;
	
	public String uploadFile(MultipartFile file) throws IOException
	{
		String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName)
				                            .key(fileName).contentType(file.getContentType()).build();
		
		s3Client.putObject(putObjectRequest,software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
		
		
		return "https://"+bucketName+".s3.amazonaws.com/"+fileName;
		
	}
	
	
}
