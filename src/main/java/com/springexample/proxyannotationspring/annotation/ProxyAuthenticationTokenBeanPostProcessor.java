package com.springexample.proxyannotationspring.annotation;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class ProxyAuthenticationTokenBeanPostProcessor implements BeanPostProcessor {
	@Autowired
	private ConfigurableListableBeanFactory configurableBeanFactory;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		if (checkAllStereotype(bean)) {
			ReflectionUtils.doWithFields(bean.getClass(), new ProxyTokenFieldCallBack(configurableBeanFactory, bean));
		}
		return bean;
	}

	private boolean checkAllStereotype(Object bean) {
		//System.out.println("ProxyAuthenticationTokenBeanPostProcessor : " + bean.getClass());
		//System.out.println("ProxyAuthenticationTokenBeanPostProcessor Anotation : " +  Arrays.deepToString(bean.getClass().getAnnotations()) + "size : " + bean.getClass().getAnnotations().length );
		return bean.getClass().isAnnotationPresent(EnableProxyAuthentication.class);
	}

}
