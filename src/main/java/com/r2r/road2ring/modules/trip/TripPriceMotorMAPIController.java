package com.r2r.road2ring.modules.trip;

import static com.r2r.road2ring.modules.common.Static.MOTORS;
import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.TRIPMOTOR;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API)
public class TripPriceMotorMAPIController {

  @Autowired
  TripPriceMotorService tripPriceMotorService;

  @GetMapping(TRIPMOTOR+"/{tripPriceId}")
  public ResponseMessage getTripMotor(@PathVariable("tripPriceId") int tripPriceId,
      Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
    }

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(tripPriceMotorService.getTripPriceMotor(tripPriceId));
    return responseMessage;
  }

}
