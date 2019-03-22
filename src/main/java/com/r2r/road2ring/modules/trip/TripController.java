package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.facility.Facility;
import com.r2r.road2ring.modules.itinerary.Itinerary;
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
  public void setTripService(TripService tripService) {
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

  @RequestMapping(value = "/{tripId}/itinerary")
  public String viewTripItinerary(@ModelAttribute Itinerary itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/itinerary/add")
  public String addTripItinerary(@ModelAttribute Itinerary itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/forms/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/facility")
  public String editTripFacility(@ModelAttribute Itinerary itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/trip-facility";
  }

  @RequestMapping(value = "/{tripId}/facility/add")
  public String addTripFacility(@ModelAttribute Facility itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/forms/trip-facility";
  }

}
