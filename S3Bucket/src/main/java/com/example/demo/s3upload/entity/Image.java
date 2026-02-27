package com.example.demo.s3upload.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String imageName;
	
	private String imageUrl;

	public Image() {
		super();
	}

	public Image(Long id, String imageName, String imageUrl) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", imageName=" + imageName + ", imageUrl=" + imageUrl + "]";
	}
	
	
}
