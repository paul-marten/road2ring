package com.r2r.road2ring.controller;

import com.r2r.road2ring.model.Account;
import com.r2r.road2ring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {

  @Autowired
  AuthService authService;

  @RequestMapping("/login")
  String admin() {
    return "login";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  ModelAndView register(ModelAndView modelAndView, Model model) {

    Account account = new Account();
    modelAndView.getModel().put("account", account);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  ModelAndView register(ModelAndView modelAndView,
      @Valid @ModelAttribute("account") Account account, BindingResult result) {
    modelAndView.setViewName("register");
    if (!result.hasErrors()) {
      authService.register(account);
      modelAndView.setViewName("redirect:/");
    }
    return modelAndView;
  }

  @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
  public String logout() {
    return "redirect:/";
  }
}
