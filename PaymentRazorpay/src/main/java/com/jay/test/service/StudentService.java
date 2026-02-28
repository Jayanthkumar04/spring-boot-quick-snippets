package com.jay.test.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jay.test.dto.StudentOrder;
import com.jay.test.repo.StudentOrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class StudentService {

	
	@Autowired
	private StudentOrderRepo studentRepo;

	
	@Value("${razorpay.key.id}")
	private String razorpayKey;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	
	private RazorpayClient client;
	
	public StudentOrder createOrder(StudentOrder stuOrder) throws Exception
	{
		
		JSONObject orderRequest = new JSONObject();
		
		orderRequest.put("amount",stuOrder.getAmount()*100 );
		
		orderRequest.put("currency", "INR");
		
		orderRequest.put("receipt", stuOrder.getEmail());
		
		this.client = new RazorpayClient(razorpayKey,razorpaySecret);
		//used to create order in razorpay
	   Order razorPayOrder = client.orders.create(orderRequest);
		
	   System.out.println(razorPayOrder);
	   stuOrder.setRazorpayOrderId(razorPayOrder.get("id"));
	   
	   stuOrder.setOrderStatus(razorPayOrder.get("status"));
	   
	   studentRepo.save(stuOrder);
	   
	   return stuOrder; 
	   
	}
	
	public String verifyPayment(String razorpayOrderId,String razorpayPaymentId,String razorpaySignature) throws RazorpayException{
		
		boolean generatedSignature = 
		        com.razorpay.Utils.verifyPaymentSignature(
		        		new JSONObject()
		                .put("razorpay_order_id", razorpayOrderId)
		                .put("razorpay_payment_id", razorpayPaymentId)
		                .put("razorpay_signature", razorpaySignature),
		            razorpaySecret
		        		);
		
		
		StudentOrder order = studentRepo.findByRazorpayOrderId(razorpayOrderId);
		
		order.setOrderStatus("PAID");
	    studentRepo.save(order);
	    
	    return "Payment Verified Successfully";
		
	}

}
