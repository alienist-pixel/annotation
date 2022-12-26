package com.springexample.proxyannotationspring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;

//@Component
public class ProxyAuthenticationTokenBeanPostProcessor implements BeanPostProcessor, InitializingBean {
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
		System.out.println("postProcessBeforeInitialization");
		return bean;
	}

	private boolean checkAllStereotype(Object bean) {
		return bean.getClass().isAnnotationPresent(RestController.class)
				|| bean.getClass().isAnnotationPresent(Component.class)
				|| bean.getClass().isAnnotationPresent(Service.class)
				|| bean.getClass().isAnnotationPresent(Repository.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}
}
