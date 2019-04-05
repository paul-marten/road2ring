package com.r2r.road2ring.modules.accessory;

import static com.r2r.road2ring.modules.common.Static.ACCESSORY;
import static com.r2r.road2ring.modules.common.Static.M_API;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + ACCESSORY)
public class AccessoryMAPIController {

  AccessoryViewService accessoryViewService;

  @Autowired
  public void setAccessoryViewService(AccessoryViewService accessoryViewService){
    this.accessoryViewService = accessoryViewService;
  }

  @GetMapping("/helm")
  public ResponseMessage getAllHelmet(Principal principal) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(accessoryViewService.getListAccessories(2));
    return responseMessage;
  }

}
