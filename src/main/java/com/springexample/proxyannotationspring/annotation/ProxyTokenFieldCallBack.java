package com.springexample.proxyannotationspring.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.springexample.proxyannotationspring.proxy.ProxyTokenGenerator;

public class ProxyTokenFieldCallBack implements FieldCallback {

	  ConfigurableListableBeanFactory configurableBeanFactory;
	  Object bean;

	  public ProxyTokenFieldCallBack(ConfigurableListableBeanFactory configurableBeanFactory, Object bean) {
	      this.configurableBeanFactory = configurableBeanFactory;
	      this.bean = bean;
	  }

	  @Override
	  public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
		  
		  if (field.isAnnotationPresent(GetAccessToken.class)) {
	          Class<?> dummyService = field.getType();
	          ReflectionUtils.makeAccessible(field);
	          Object instance = getBeanInstance(dummyService.getSimpleName(), dummyService);
	          field.set(bean, (String) new ProxyTokenGenerator().getAccessToken());
	      }
		  
	  }

	  public Object getBeanInstance(String beanName, Class clazz) {
	      Object proxyInstance = null;
	      
	      if (!configurableBeanFactory.containsBean(beanName)) {
	          Object newInstance = null;
	          try {
	              Constructor<?> ctr = clazz.getConstructor();
	              newInstance = ctr.newInstance();
	          } catch (Exception e) {
	              throw new RuntimeException(e);
	          }

	          proxyInstance = configurableBeanFactory.initializeBean(newInstance, beanName);
	          configurableBeanFactory.autowireBeanProperties(proxyInstance, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
	          configurableBeanFactory.registerSingleton(beanName, proxyInstance);
	      } else {
	    	  proxyInstance = configurableBeanFactory.getBean(beanName);
	      }
	      return proxyInstance;
	  }
	  
	  

	

}
