package com.r2r.road2ring.modules.motor;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Motor motor, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(motorService.saveMotor(motor));
    model.addAttribute("response", response);

    return "redirect:/home";
  }

}
