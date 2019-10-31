package com.fh.shop.api.config;

import com.fh.shop.api.Interceptor.IdempotentInterceptor;
import com.fh.shop.api.Interceptor.LoginInterceptor;
import com.fh.shop.api.resolvers.MemberVoArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(getIdempotentInterceptor()).addPathPatterns("/**");
  }
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(getMemberVoArgumentResolver());
  }

  @Bean
  public MemberVoArgumentResolver getMemberVoArgumentResolver(){
    return new MemberVoArgumentResolver();
  }
  @Bean
  public LoginInterceptor getLoginInterceptor(){
    return new LoginInterceptor();
  }

  @Bean
  public IdempotentInterceptor getIdempotentInterceptor(){
    return new IdempotentInterceptor();
  }

}
