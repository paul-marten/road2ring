package com.r2r.road2ring.modules.trip;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.REQUEST_TRIP;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.user.User;
import com.r2r.road2ring.modules.user.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + REQUEST_TRIP)
public class RequestTripMAPIController {

  RequestTripService requestTripService;

  UserService userService;

  @Autowired
  public void setRequestTripService(RequestTripService requestTripService){
    this.requestTripService = requestTripService;
  }

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/create")
  public ResponseMessage createRequestTrip(
      @ModelAttribute RequestTrip requestTrip, Principal principal,
      HttpServletResponse httpStatus){
    ResponseMessage responseMessage = new ResponseMessage();
    if(principal != null){
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      User user = userService.findUserByEmail(currentConsumer.getUsername());

      responseMessage.setCode(200);
      responseMessage.setObject(requestTripService.saveRequestTrip(user,requestTrip));
      responseMessage.setMessage("Already create request");
      httpStatus.setStatus(HttpStatus.OK.value());

    }else {
      httpStatus.setStatus(HttpStatus.BAD_REQUEST.value());
      responseMessage.setCode(703);
      responseMessage.setMessage("Please login first");
    }
    return responseMessage;
  }

}
