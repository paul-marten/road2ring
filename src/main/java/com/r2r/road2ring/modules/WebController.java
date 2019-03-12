package com.r2r.road2ring.modules;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/home")
  public String home(){
    return "admin/page/index";
  }


  @GetMapping("/login")
  public String login(){
    return "admin/page/login";
  }
}
