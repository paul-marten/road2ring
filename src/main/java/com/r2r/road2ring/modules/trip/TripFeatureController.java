package com.r2r.road2ring.modules.trip;

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
@RequestMapping(value = "/trip-feature")
public class TripFeatureController {

  TripFeatureService tripFeatureService;

  @Autowired
  public void setTripFeatureService(TripFeatureService tripFeatureService) {
    this.tripFeatureService = tripFeatureService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    return "admin/page/trip-feature";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    TripFeature tripFeature = new TripFeature();
    response.setObject(tripFeature);

    model.addAttribute("response", response);
    return "admin/forms/trip-feature";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    TripFeature tripFeature = tripFeatureService.getTripFeatureById(id);
    response.setObject(tripFeature);

    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/trip-feature";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute TripFeature tripFeature, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(tripFeatureService.save(tripFeature));
    model.addAttribute("response", response);

    return "redirect:/trip-feature";
  }

}
