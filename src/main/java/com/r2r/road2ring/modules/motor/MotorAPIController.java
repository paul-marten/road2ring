package com.r2r.road2ring.modules.motor;

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
@RequestMapping(value = "/api/motor")
public class MotorAPIController {

  MotorService motorService;

  @Autowired
  public void setMotorService(MotorService motorService) {
    this.motorService = motorService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  public List<Motor> datatable(
      HttpServletRequest request) {
    return motorService.getAllMotor();
  }
}
