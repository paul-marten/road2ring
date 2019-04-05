package com.r2r.road2ring.modules.motor;

import static com.r2r.road2ring.modules.common.Static.MOTOR;
import static com.r2r.road2ring.modules.common.Static.M_API;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + MOTOR)
public class MotorMAPIController {

  MotorService motorService;

  @Autowired
  public void setMotorService(MotorService motorService){
    this.motorService = motorService;
  }

  @GetMapping("/list-motor/{page}/{limit}")
  public ResponseMessage getAllMotor(
      @PathVariable(value = "page") Integer page,
      @PathVariable(value = "limit") Integer limit,
      Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      System.out.println(currentConsumer.getUsername());
    }

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(motorService.getListMotor(page,limit));
    return responseMessage;
  }

}
