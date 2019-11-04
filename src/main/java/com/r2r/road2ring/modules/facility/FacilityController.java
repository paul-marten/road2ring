package com.r2r.road2ring.modules.facility;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/facility")
public class FacilityController {

  FacilityService facilityService;

  @Autowired
  public void setFacilityService(FacilityService facilityService){
    this.facilityService = facilityService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/facility";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Facility facility = new Facility();
    response.setObject(facility);
    model.addAttribute("response", response);
    return "admin/forms/facility";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    Facility facility = facilityService.getFacilityById(id);
    response.setObject(facility);
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/facility";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Facility facility, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(facilityService.saveFacility(facility));
    model.addAttribute("response", response);
    return "redirect:/facility";
  }

}
