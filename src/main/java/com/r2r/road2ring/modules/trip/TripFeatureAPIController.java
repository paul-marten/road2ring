package com.r2r.road2ring.modules.trip;

import static com.r2r.road2ring.modules.common.Static.API;
import static com.r2r.road2ring.modules.common.Static.TRIP;
import static com.r2r.road2ring.modules.common.Static.TRIP_FEATURE;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = API + TRIP_FEATURE)
public class TripFeatureAPIController {

  TripFeatureService tripFeatureService;

  @Autowired
  public void setTripFeatureService(TripFeatureService tripFeatureService) {
    this.tripFeatureService = tripFeatureService;
  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  public List<TripFeature> datatable(HttpServletRequest request) {
    return tripFeatureService.getDatatableContents();
  }

  @PostMapping("/update-publish-status")
  public ResponseMessage changeStatus(@ModelAttribute TripFeature tripFeature,HttpServletRequest request) {

    ResponseMessage response = new ResponseMessage();

    response.setObject(tripFeatureService.updatePublishStatus(tripFeature));

    return response;
  }
}
