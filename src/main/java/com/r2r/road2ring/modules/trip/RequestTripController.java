package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/request-trip")
public class RequestTripController {

  RequestTripService requestTripService;

  @Autowired
  public void setRequestTripService(RequestTripService requestTripService) {
    this.requestTripService = requestTripService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/request-trip";
  }
}
