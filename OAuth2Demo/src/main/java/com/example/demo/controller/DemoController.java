package com.example.demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	
	@GetMapping("/public")
	public String getPublic()
	{
		return "this is public";
	}
	@GetMapping("/private")
	public String getPrivate(OAuth2AuthenticationToken token)
	{
		return "i am private name:==>"+token.getPrincipal().getAttribute("name")+" email:==> "+token.getPrincipal().getAttribute("email");
	}
}
