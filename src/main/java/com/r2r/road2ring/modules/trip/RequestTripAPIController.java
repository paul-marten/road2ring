package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/request-trip")
public class RequestTripAPIController {

  RequestTripService requestTripService;

  @Autowired
  public void setRequestTripService(RequestTripService requestTripService) {
    this.requestTripService = requestTripService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DefaultRequestTrip.class)
  public List<RequestTrip> datatable(HttpServletRequest request) {

    return requestTripService.getAllRequest();
//    return tripService.getAllTrip();
  }
}
