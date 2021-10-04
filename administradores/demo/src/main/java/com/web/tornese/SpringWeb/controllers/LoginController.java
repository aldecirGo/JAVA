package com.web.tornese.SpringWeb.controllers;

import com.web.tornese.SpringWeb.models.Administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.tornese.SpringWeb.repositorys.AdminitratorRepository;
import com.web.tornese.SpringWeb.service.CookieService;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
	
 @Autowired
 private AdminitratorRepository repo;
   
  @GetMapping("/login")
  public String index(){
	  return "login/index";
  }
  
  @PostMapping("/connect")
  public String connection(Model model,Administrator admParam,  String remember, HttpServletResponse response)
  {
	  boolean  bRemember = false;
    Administrator adm =  this.repo.login(admParam.getEmail(), admParam.getPassword());
    if (remember != null){
      bRemember = true;
    }
    if( adm != null )
    {
      CookieService.setCookie(response, "userId", String.valueOf( adm.getId()), 90, bRemember);
      CookieService.setCookie(response, "userName", String.valueOf( adm.getName()), 90, bRemember);
      model.addAttribute("name", String.valueOf(adm.getName()));
      return "redirect:/";
    }	  
    model.addAttribute("erro", "Usuário / Senha inválidos!");
	  return "/login";
  }
  @GetMapping("/disconnect")
  public String disconnection(Model model,  HttpServletResponse response)
  {
	  
    CookieService.setCookie(response, "userId", "", 0, false);
    CookieService.setCookie(response, "userName", "", 0, false);
     	  
    model.addAttribute("erro", "Usuário Desconectado!");
	  return "/login";
  }
  
}
