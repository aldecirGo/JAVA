package com.web.tornese.SpringWeb.controllers;
import com.web.tornese.SpringWeb.models.Administrator;
import com.web.tornese.SpringWeb.repositorys.AdminitratorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminstratorController {
  @Autowired
  private AdminitratorRepository  adminitratorRepository;
	
  @GetMapping("/administrators")
  public String index(Model model){
	  List<Administrator> administrator = (List<Administrator>) adminitratorRepository.findAll();
	  model.addAttribute("administrators", administrator);
	  return "administrators/index";
  }
  
  @GetMapping("/administrators/new")
  public String admNew(){
	  return "administrators/new";
  }
  
  @PostMapping("/administrators/create")
  public String admCreate(Administrator adm){
	  adminitratorRepository.save(adm);
	  return "redirect:/administrators";
  }

  @PostMapping("/administrators/delete")
  public String admDelete(Administrator adm) {
    adminitratorRepository.deleteById(adm.getId());   
    return "redirect:/administrators";
  }

  @PostMapping("/administrators/edit")
  public String admEdit( @RequestParam("id") int id, Model model){    
    Optional<Administrator> adm = adminitratorRepository.findById(id);    
    if (adminitratorRepository.exists(id)){      
      model.addAttribute("administrator", adm.get());
      return "administrators/edit";
    }else{
      return "redirect:/administrators";
    }
  }

  @PostMapping("/administrators/update")
  public String admUpdate(Administrator adm){
    if (adminitratorRepository.exists(adm.getId())){
      adminitratorRepository.save(adm);
    }
	  return "redirect:/administrators";
  }  
}
