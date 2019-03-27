package com.r2r.road2ring.modules.facility;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/facility")
public class FacilityAPIController {

  FacilityService facilityService;

  @Autowired
  public void setFacilityService(FacilityService facilityService) {
    this.facilityService = facilityService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
//  @JsonView(ResponseView..class)
  public List<Facility> datatable(
      HttpServletRequest request) {

    return facilityService.getAllFacility();
  }
}
