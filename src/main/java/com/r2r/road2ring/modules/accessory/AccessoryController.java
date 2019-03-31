package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.facility.Facility;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/accessory")
public class AccessoryController {
  AccessoryService accessoryService;

  @Autowired
  public void setAccessoryService(AccessoryService accessoryService) {
    this.accessoryService = accessoryService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/accessory";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Accessory facility = new Accessory();
    response.setObject(facility);
    model.addAttribute("response", response);
    return "admin/forms/accessory";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    Accessory accessory = accessoryService.getAccessoryById(id);
    response.setObject(accessory);
    model.addAttribute("response", response);
    return "admin/forms/accessory";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Accessory accessory, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(accessoryService.saveAccessory(accessory));
    model.addAttribute("response", response);
    return "redirect:/accessory";
  }
}