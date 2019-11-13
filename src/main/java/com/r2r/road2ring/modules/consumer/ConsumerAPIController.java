package com.r2r.road2ring.modules.consumer;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;

import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.common.Static;
import com.r2r.road2ring.modules.role.Role;
import com.r2r.road2ring.modules.user.User;
import com.r2r.road2ring.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/consumer")
public class ConsumerAPIController {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public ResponseMessage createConsumer(
      @ModelAttribute User consumer,
      Principal principal, HttpServletResponse httpStatus) throws Road2RingException {
    ResponseMessage responseMessage = new ResponseMessage();
    if(principal != null){
      Authentication auth = (Authentication) principal;
      User consumerDetail = userService.findUserByEmail(auth.getName());
      if(consumerDetail.getRole().getName().matches("ROLE_ADMINISTRATOR")){
        Role role = new Role();
        role.setId(1);
        consumer.setRole(role);
        consumer.setActivation(Static.IS_ACTIVE);
        responseMessage.setObject(userService.register(consumer, null));
        responseMessage.setCode(200);
        httpStatus.setStatus(HttpStatus.OK.value());
        responseMessage.setMessage("User created");
      } else {
        httpStatus.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMessage.setCode(703);
        responseMessage.setMessage("You are not authorized");
      }
    } else {
      httpStatus.setStatus(HttpStatus.BAD_REQUEST.value());
      responseMessage.setCode(703);
      responseMessage.setMessage("Please login first");
    }

    return responseMessage;
  }

}
