package com.web.tornese.SpringWeb.service.Autenticacao;
import com.web.tornese.SpringWeb.service.CookieService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{

  @Override
  public boolean preHandle(HttpServletRequest resquest, HttpServletResponse response, Object handler)
  throws Exception{
       //System.err.println("Pre Handle method is Calling - " );
      CookieService cok = new CookieService();
      if(cok.getCookie(resquest, "userId") != null){
        return true;
      }
      response.sendRedirect("/login");
      return false; 
     
  }
/*
  @Override
  public void postHandle(HttpServletRequest resquest, HttpServletResponse response, Object handler,
            ModelAndView modelAndView)
  throws Exception{
    System.out.println("Post Handle method is Calling - ");     
  }
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex)
  throws Exception{
    System.out.println("Request Handle method is Calling - ");     
  }
*/

  
}
