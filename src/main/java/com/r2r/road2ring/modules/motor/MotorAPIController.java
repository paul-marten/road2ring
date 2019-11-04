package com.r2r.road2ring.modules.motor;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    return motorService.getAllMotorDatatable();
  }

  @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
  public ResponseMessage autocomplete(@RequestParam("keyword") String keyword,
      HttpServletRequest request) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(motorService.getAutocompletPublishedMotorByTitle(keyword));
    return responseMessage;
  }

  @PostMapping("/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusTrip(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      motorService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }


}
