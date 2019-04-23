package com.r2r.road2ring.modules.consumer;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
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

  private ConsumerService consumerService;

  @Autowired
  public void setConsumerService(ConsumerService consumerService){
    this.consumerService = consumerService;
  }

  @PostMapping("/create")
  public ResponseMessage createConsumer(
      @ModelAttribute Consumer consumer,
      Principal principal, HttpServletResponse httpStatus){
    ResponseMessage responseMessage = new ResponseMessage();
    if(principal != null){
      Authentication auth = (Authentication) principal;
      Consumer consumerDetail = consumerService.getUserByEmail(auth.getName());
      if(consumerDetail.getRole().getName().matches("ROLE_ADMINISTRATOR")){
        responseMessage.setObject(consumerService.saveUser(consumer));
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
