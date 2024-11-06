package com.fullstack.ems.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class EmsInterceptorAppConfig implements WebMvcConfigurer {
   @Autowired
   EmsInterceptor emsInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(emsInterceptor);
   }
}
