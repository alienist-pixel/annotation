package com.springexample.proxyannotationspring.aspect;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.springexample.proxyannotationspring.annotation.ProxyTokenFieldCallBack;

@Aspect
@Component
public class ProxyOAuth2Aspect {
	
	private ProxyOAuth2Aspect() {
	}
	
	@Autowired
	private ConfigurableListableBeanFactory configurableBeanFactory;

	static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Aspect Logger");

	@Before(value = "@within(com.springexample.proxyannotationspring.annotation.EnableProxyAuthentication) "
			+ "|| @annotation(com.springexample.proxyannotationspring.annotation.EnableProxyAuthentication)")
	private void log(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//System.out.println("ProxyOAuth2Aspect : " + joinPoint.getTarget().getClass());
		//System.out.println("ProxyOAuth2Aspect Anotation : " +  Arrays.deepToString(joinPoint.getTarget().getClass().getAnnotations()) + "size : " + joinPoint.getTarget().getClass().getAnnotations().length );
        ReflectionUtils.doWithFields(joinPoint.getTarget().getClass(), new ProxyTokenFieldCallBack(configurableBeanFactory, joinPoint.getTarget()));
	}

}
