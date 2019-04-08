package com.r2r.road2ring.modules.trip;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API)
public class TripMAPIController {

  TripViewService tripViewService;

  TripPriceService tripPriceService;

  @Autowired
  public void setTripViewService(TripViewService tripViewService) {
    this.tripViewService = tripViewService;
  }

  @Autowired
  public void setTripPriceService(TripPriceService tripPriceService){
    this.tripPriceService = tripPriceService;
  }

  @GetMapping( TRIPS + "/{page}/{limit}")
  public ResponseMessage getAllTrip(
      @PathVariable(value = "page") Integer page,
      @PathVariable(value = "limit") Integer limit,
      Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      System.out.println(currentConsumer.getUsername());
    }

    ResponseMessage responseMessage = tripViewService.getListTripView(page, limit);
    return responseMessage;
  }

  @GetMapping(TRIP + "/{tripId}")
  public ResponseMessage getAllTrip(
      @PathVariable("tripId") Integer tripId,
      Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      System.out.println(currentConsumer.getUsername());
    }

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(tripViewService.getDetailTripView(tripId));
    return responseMessage;
  }

  @GetMapping("/price/{tripId}")
  public ResponseMessage getAllTripPrice(
      @PathVariable("tripId") Integer tripId,
      Principal principal) {

    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      System.out.println(currentConsumer.getUsername());
    }

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(tripPriceService.bindListTripPriceView(tripId));
    return responseMessage;
  }
}
