package com.r2r.road2ring.modules.roadcaptain;

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
@RequestMapping(value = "/captain")
public class RoadCaptainController {

  RoadCaptainService roadCaptainService;

  @Autowired
  public void setRoadCaptainService(
      RoadCaptainService roadCaptainService) {
    this.roadCaptainService = roadCaptainService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/roadCaptain";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    RoadCaptain roadCaptain = new RoadCaptain();
    response.setObject(roadCaptain);
    model.addAttribute("response", response);
    return "admin/forms/roadCaptain";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());
    RoadCaptain roadCaptain = roadCaptainService.getCaptainById(id);
    response.setObject(roadCaptain);
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/roadCaptain";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute RoadCaptain roadCaptain, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(roadCaptainService.saveRoadCaptain(roadCaptain));
    model.addAttribute("response", response);

    return "redirect:/captain";
  }
}
