package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/trip")
public class TripAPIController {

  TripService tripService;

  @Autowired
  public void setTripService(TripService tripService) {
    this.tripService = tripService;
  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public DataTablesOutput<Trip> datatable(@Valid DataTablesInput input,
      HttpServletRequest request) {

    System.out.println(input);

    return tripService.getDatatableContents(input);
//    return tripService.getAllTrip();
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<Trip> datatable(
      HttpServletRequest request) {

    return tripService.getAllTrip();
  }

  @RequestMapping(value = "/{tripId}/facility/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<TripFacility> datatableFacility(@PathVariable("tripId") int id,
      HttpServletRequest request) {
    System.out.println(request.getRequestURI());

    return tripService.getTripFacility(id);
  }

  @RequestMapping(value = "/{tripId}/itinerary/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<Itinerary> datatableItinerary(@PathVariable("tripId") int id,
      HttpServletRequest request) {

    return tripService.getTripItinerary(id);
  }

}
