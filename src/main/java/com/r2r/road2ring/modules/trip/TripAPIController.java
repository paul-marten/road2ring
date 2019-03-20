package com.r2r.road2ring.modules.trip;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

//  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
//  public DataTablesOutput<Trip> datatable(@Validated DataTablesInput input,
//      HttpServletRequest request) {
//
//    System.out.println(input);
//
//    return tripService.getDatatableContents(input);
////    return tripService.getAllTrip();
//  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  public List<Trip> datatable(
      HttpServletRequest request) {

//    return tripService.getDatatableContents(input);
    return tripService.getAllTrip();
  }

}
