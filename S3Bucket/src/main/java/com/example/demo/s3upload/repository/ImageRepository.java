package com.example.demo.s3upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.s3upload.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
