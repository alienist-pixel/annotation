package com.springexample.proxyannotationspring.controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springexample.proxyannotationspring.annotation.EnableProxyAuthentication;
import com.springexample.proxyannotationspring.annotation.GetAccessToken;

//@RestController
//@EnableProxyAuthentication
public class TestController {
	
	@Override
	public int hashCode() {
		System.out.println(getClass()+" "+Objects.hash(token));
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestController other = (TestController) obj;
		return Objects.equals(token, other.token);
	}

	@GetAccessToken
	public String token;
	
	String tokenTest;

	@GetMapping(value = "/annotation")
	public void testAnnotation() {
		System.out.println("called "+ getClass() +"  " + token );
	}
	
	

}
