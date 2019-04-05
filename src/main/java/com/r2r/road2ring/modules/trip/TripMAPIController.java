package com.r2r.road2ring.modules.trip;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.TRIP;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.consumer.ConsumerDetail;
import com.r2r.road2ring.modules.user.User;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + TRIP)
public class TripMAPIController {

  TripViewService tripViewService;

  @Autowired
  public void setTripViewService(TripViewService tripViewService) {
    this.tripViewService = tripViewService;
  }

  @GetMapping("/list-trip")
  public ResponseMessage getAllTrip(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      Principal principal
  ) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      System.out.println(currentConsumer.getUsername());
    }

    ResponseMessage responseMessage = tripViewService.getListTripView(page, limit);
    return responseMessage;
  }

}
