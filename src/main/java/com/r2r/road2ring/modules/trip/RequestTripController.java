package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.ResponseMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String viewDetail(Model model, @RequestParam int id, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    RequestTrip requestTrip = requestTripService.findRequest(id);
    response.setObject(requestTrip);
    model.addAttribute("response", response);
    return "admin/forms/request-trip";
  }
}
