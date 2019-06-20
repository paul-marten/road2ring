package com.r2r.road2ring.modules.accessory;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.facility.Facility;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessory")
public class AccessoryAPIController {

  AccessoryService accessoryService;

  @Autowired
  public void setAccessoryService(AccessoryService accessoryService) {
    this.accessoryService = accessoryService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DefaultAccessory.class)
  public List<Accessory> datatable(
      HttpServletRequest request) {

    return accessoryService.getAllAccessory();
  }

  @PostMapping("/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusTrip(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      accessoryService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }
}
