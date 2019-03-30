package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.facility.Facility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Accessory> datatable(
      HttpServletRequest request) {

    return accessoryService.getAllAccessory();
  }
}
