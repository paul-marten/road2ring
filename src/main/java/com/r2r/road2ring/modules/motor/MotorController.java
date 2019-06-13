package com.r2r.road2ring.modules.motor;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/motor")
public class MotorController {

  MotorService motorService;

  @Autowired
  public void setMotorService(MotorService motorService){
    this.motorService = motorService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/motor";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Motor motor = new Motor();
    response.setObject(motor);
    model.addAttribute("response", response);
    return "admin/forms/motor";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());
    Motor motor = motorService.getMotoyrById(id);
    response.setObject(motor);
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/motor";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Motor motor, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(motorService.saveMotor(motor));
    model.addAttribute("response", response);

    return "redirect:/motor";
  }

}
