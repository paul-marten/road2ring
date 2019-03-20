package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/trip")
public class TripController {

  TripService tripService;

  @Autowired
  public void setTripService(TripService tripService){
    this.tripService = tripService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/trip";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Trip trip = new Trip();
    response.setObject(trip);
    model.addAttribute("response", response);
    return "admin/forms/trip";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Trip trip, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(tripService.saveTrip(trip));
    model.addAttribute("response", response);

    return "redirect:/home";
  }

}
