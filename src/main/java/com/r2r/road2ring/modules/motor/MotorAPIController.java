package com.r2r.road2ring.modules.motor;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/motor")
public class MotorAPIController {

  MotorService motorService;

  @Autowired
  public void setMotorService(MotorService motorService){
    this.motorService = motorService;
  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  public DataTablesOutput<Motor> getMotors(@Valid DataTablesInput input) {
    return motorService.findDatatable(input);
  }

}
