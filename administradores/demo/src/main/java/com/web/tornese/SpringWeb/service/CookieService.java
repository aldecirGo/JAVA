package com.web.tornese.SpringWeb.service;


//import java.util.Arrays;
//import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieService {

  public static void setCookie(HttpServletResponse response, String key, String value, int intSecond, boolean bCheck){
    
    Cookie cookie = new Cookie(key, value);
    if (bCheck){
      cookie.setMaxAge(60 * 60 * 30);     }
    else{
      cookie.setMaxAge(intSecond * 60);    }    
    response.addCookie(cookie);
  }
  public  String getCookie(HttpServletRequest request, String key){
    String strCookie = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null){
      for ( int i=0; i<request.getCookies().length; i++) {
        Cookie cookie = request.getCookies()[i];
        if (key.equals(cookie.getName())){
          strCookie = String.valueOf(cookie.getValue());
        }
      }
    }
    return strCookie;
  }
}
