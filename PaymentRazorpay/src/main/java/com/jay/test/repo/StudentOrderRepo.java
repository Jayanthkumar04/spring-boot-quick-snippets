package com.jay.test.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.test.dto.StudentOrder;

@Repository
public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer>{

	
	StudentOrder findByRazorpayOrderId(String id);
}
