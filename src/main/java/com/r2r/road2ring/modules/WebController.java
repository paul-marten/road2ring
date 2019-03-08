package com.r2r.road2ring.modules;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/home")
  public String test(){
    return "admin/page/index";
  }
}
