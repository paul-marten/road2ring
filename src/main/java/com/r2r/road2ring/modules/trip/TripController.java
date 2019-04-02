package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.TripFacility.TripFacilityService;
import com.r2r.road2ring.modules.facility.Facility;
import com.r2r.road2ring.modules.facility.FacilityService;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.itinerary.ItineraryService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/trip")
public class TripController {

  TripService tripService;

  ItineraryService itineraryService;

  TripFacilityService tripFacilityService;

  FacilityService facilityService;

  @Autowired
  public void setFacilityService(FacilityService facility) {
    this.facilityService = facility;
  }

  @Autowired
  public void setTripService(TripService tripService) {
    this.tripService = tripService;
  }

  @Autowired
  public void setItineraryService(ItineraryService itineraryService) {
    this.itineraryService = itineraryService;
  }

  @Autowired
  public void setTripFacilityService(TripFacilityService tripFacility) {
    this.tripFacilityService = tripFacility;
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
    List<Facility> facilityList = facilityService.getAllFacility();
    response.setObject(trip);
    model.addAttribute("response", response);
    model.addAttribute("facilities", facilityList);
    return "admin/forms/trip";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    Trip trip = tripService.getTripById(id);
//    List<TripFacility> tripFacilities = tripFacilityService.getTripFacilityOnTrip(id);
    List<Facility> facilityList = facilityService.getAllFacility();
    List<Integer> checked = new ArrayList<Integer>(Arrays.asList(10,3));

    response.setObject(trip);
    model.addAttribute("response", response);
    model.addAttribute("facilities", facilityList);
    model.addAttribute("checked", checked);
    return "admin/forms/trip";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Trip trip, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(tripService.saveTrip(trip));
    model.addAttribute("response", response);

    return "redirect:/trip";
  }

  @RequestMapping(value = "/{tripId}/itinerary/save")
  public String saveTripItinerary(@PathVariable("tripId") int id, @ModelAttribute Trip trip, Model model, Principal principal){
    itineraryService.saveListOfItinerary(trip.getItineraries(), trip);
    return "redirect:/trip/"+id+"/itinerary";
  }

//  @RequestMapping(value = "/{tripId}/facility/save")
//  public String saveTripFacility(@PathVariable("tripId") int id, @ModelAttribute Trip trip, Model model, Principal principal){
//    tripFacilityService.saveListOfTripFacility(trip.getTripFacilities(), trip);
//    return "redirect:/trip/"+id+"/facility";
//  }

  @RequestMapping(value = "/{tripId}/itinerary")
  public String viewTripItinerary(@ModelAttribute Itinerary itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/itinerary/add")
  public String addTripItinerary(@PathVariable("tripId") int id, @ModelAttribute Itinerary itinerary, Model model) {
    Trip trip = tripService.getTripById(id);
    ResponseMessage response = new ResponseMessage();
    response.setObject(trip);
    model.addAttribute("response", response);
    model.addAttribute("action", "/trip/"+id+"/itinerary/save");
    model.addAttribute("tripId", id);
    return "admin/forms/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/itinerary/edit")
  public String editTripItinerary(@PathVariable("tripId") int id, @ModelAttribute Itinerary itinerary, Model model) {
    Trip trip = tripService.getTripById(id);
    ResponseMessage response = new ResponseMessage();
    response.setObject(trip);
    model.addAttribute("response", response);
    model.addAttribute("action", "/trip/"+id+"/itinerary/save");
    model.addAttribute("tripId", id);
    return "admin/forms/trip-itinerary";
  }

  @RequestMapping(value = "/{tripId}/facility")
  public String editTripFacility(@PathVariable("tripId") int id, @ModelAttribute Itinerary itinerary, Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/trip-facility";
  }

  @RequestMapping(value = "/{tripId}/facility/add")
  public String addTripFacility(@PathVariable("tripId") int id, @ModelAttribute Facility itinerary, Model model) {
    Trip trip = tripService.getTripById(id);
    ResponseMessage response = new ResponseMessage();
    response.setObject(trip);
    model.addAttribute("action", "/trip/"+id+"/facility/save");
    model.addAttribute("response", response);
    return "admin/forms/trip-facility";
  }

}
