package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.Response;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    Response response = new Response();
    model.addAttribute("response", response);
    return "admin/page/trip";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    Response response = new Response();
    Trip trip = new Trip();
    response.setObject(trip);
    model.addAttribute("response", response);
    return "admin/forms/trip";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Trip trip, Model model, Principal principal) {
    Response response = new Response();
    response.setObject(tripService.saveTrip(trip));
    model.addAttribute("response", response);

    return "redirect:/home";
  }

  @RequestMapping(value = "/{tripId}/itinerary")
  public String editTripItinerary(@ModelAttribute Itinerary itinerary, Model modelm, Principal principal){

    return "admin/forms/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/facility")
  public String editTripFacility(@ModelAttribute Itinerary itinerary, Model modelm, Principal principal){

    return "admin/forms/trip-facility";
  }

}
