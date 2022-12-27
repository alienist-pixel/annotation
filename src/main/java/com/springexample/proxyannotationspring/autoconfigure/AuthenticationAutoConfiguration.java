package com.springexample.proxyannotationspring.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springexample.proxyannotationspring.config.OAuthProperties;
import com.springexample.proxyannotationspring.config.ProxyOauthConfiguration;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
public class AuthenticationAutoConfiguration {
	
	@Autowired
	private OAuthProperties oauthProperties;
	
	@Bean
    @ConditionalOnMissingBean
    public ProxyOauthConfiguration proxyOAuthConfig() {

        String host = oauthProperties.getHost() == null ? System.getProperty("proxy.oauth.okta") : oauthProperties.getHost();
        
       // System.out.println(getClass() +" "+ host);
    	ProxyOauthConfiguration.getInstance().put("host", host);
        
        return ProxyOauthConfiguration.getInstance();
    }
}
