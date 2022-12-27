package com.springexample.proxyannotationspring.controller;

import org.springframework.boot.CommandLineRunner;

import com.springexample.proxyannotationspring.annotation.GetAccessToken;

//@Component
//@EnableProxyAuthentication
public class ComponentTest implements CommandLineRunner{

	@GetAccessToken
	public String testToken;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(getClass() + " : "+ testToken);
	}

}
