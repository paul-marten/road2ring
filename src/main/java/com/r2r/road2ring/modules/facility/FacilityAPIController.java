package com.r2r.road2ring.modules.facility;

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
  public void setFacilityService(FacilityService facilityService){
    this.facilityService = facilityService;
  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  public DataTablesOutput<Facility> getFacilities(@Valid DataTablesInput input) {
    return facilityService.findDatatable(input);
  }

}
