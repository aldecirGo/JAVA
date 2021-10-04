package com.web.tornese.SpringWeb.controllers;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.service.CookieService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
   
  @GetMapping("/")
  public String index(Model model, HttpServletRequest request){

    CookieService  cs = new CookieService();
    model.addAttribute("userName",String.valueOf( cs.getCookie(request, "userName") ));
	  model.addAttribute("nome", String.valueOf( cs.getCookie(request, "userName") ));
    model.addAttribute("mensagem", "Olá pessoal");
	  return "home/index";
  }
  
}
