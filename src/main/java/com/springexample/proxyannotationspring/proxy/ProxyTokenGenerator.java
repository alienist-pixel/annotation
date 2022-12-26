package com.springexample.proxyannotationspring.proxy;

import java.nio.charset.Charset;
import java.util.Random;

public class ProxyTokenGenerator {
	
	public String getAccessToken() {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    return "The test token " + new String(array, Charset.forName("UTF-8"));
	}

}
