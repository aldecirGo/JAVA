package com.web.tornese.SpringWeb.service.Autenticacao;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class LoginInterceptorAppConfig extends WebMvcConfigurerAdapter{

  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new LoginInterceptor())
      .excludePathPatterns(
        "/connect",
        "/login",
        "/error",
        "/conection",
        "/js/**",
        "/vendor/**",
        "/img/**",
        "/css/**",
        "/scss/**",
        "/favicon.ico"
      );

  }

  
}
