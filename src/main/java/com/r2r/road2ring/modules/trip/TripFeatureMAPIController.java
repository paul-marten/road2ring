package com.r2r.road2ring.modules.trip;

import static com.r2r.road2ring.modules.common.Static.API;
import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.TRIP;
import static com.r2r.road2ring.modules.common.Static.TRIPS;

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
public class TripFeatureMAPIController {

  @Autowired
  TripFeatureService tripFeatureService;


  @GetMapping( TRIPS + "/feature")
  public ResponseMessage getAllTrip(Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
    }

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(tripFeatureService.getTripFeature());
    return responseMessage;
  }

}
