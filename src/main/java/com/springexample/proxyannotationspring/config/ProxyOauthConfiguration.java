package com.springexample.proxyannotationspring.config;

import java.util.HashMap;
import java.util.Map;

public class ProxyOauthConfiguration {
	
	public static ProxyOauthConfiguration instance;
	Map<String, String> configurationMap;
	
	private ProxyOauthConfiguration() {
			configurationMap = new HashMap<>();
	}
	

	public void put(String key, String host) {
		configurationMap.put(key, host);
	}
	
	public String get(String key) {
		return configurationMap.get(key);
	}
	
	public static ProxyOauthConfiguration getInstance() {
		if(instance==null) {
			 instance = new ProxyOauthConfiguration();
		}
		return instance;
	}

}
