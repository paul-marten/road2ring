package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.accessorycategory.AccessoryCategory;
import com.r2r.road2ring.modules.accessorycategory.AccessoryCategoryService;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.facility.Facility;
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
@RequestMapping(value = "/accessory")
public class AccessoryController {
  AccessoryService accessoryService;

  AccessoryCategoryService accessoryCategoryService;

  @Autowired
  public void setAccessoryService(AccessoryService accessoryService) {
    this.accessoryService = accessoryService;
  }

  @Autowired
  public void setAccessoryCategoryService(AccessoryCategoryService accessoryCategoryService){
    this.accessoryCategoryService = accessoryCategoryService;
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
    facility.setAccessoryCategory(new AccessoryCategory());
    response.setObject(facility);
    model.addAttribute("categories", accessoryCategoryService.getAccessoryCategories());
    model.addAttribute("response", response);
    return "admin/forms/accessory";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id,HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    Accessory accessory = accessoryService.getAccessoryById(id);
    response.setObject(accessory);
    model.addAttribute("categories", accessoryCategoryService.getAccessoryCategories());
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);
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
