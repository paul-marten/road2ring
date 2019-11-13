package com.r2r.road2ring.modules.accessorycategory;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/accessory-category")
public class AccessoryCategoryController {

  AccessoryCategoryService accessoryCategoryService;

  AccessorySubCategoryService accessorySubCategoryService;

  @Autowired
  public void setAccessoryCategoryService(AccessoryCategoryService accessoryCategoryService){
    this.accessoryCategoryService = accessoryCategoryService;
  }

  @Autowired
  public void setAccessorySubCategoryService(AccessorySubCategoryService accessorySubCategoryService){
    this.accessorySubCategoryService = accessorySubCategoryService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/accessory-category";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    AccessoryCategory accessoryCategory = new AccessoryCategory();
    response.setObject(accessoryCategory);
    model.addAttribute("response", response);
    return "admin/forms/accessory-category";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    AccessoryCategory accessoryCategory = accessoryCategoryService.getAccessoryCategoryById(id);
    response.setObject(accessoryCategory);
    model.addAttribute("response", response);
    return "admin/forms/accessory-category";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute AccessoryCategory accessoryCategory, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(accessoryCategoryService.saveAccessoryCategory(accessoryCategory));
    model.addAttribute("response", response);

    return "redirect:/accessory-category";
  }

  @RequestMapping(value = "/sub-category", method = RequestMethod.GET)
  public String indexSubCategory(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/accessory-subcategory";
  }

  @RequestMapping(value = "/sub-category/add", method = RequestMethod.GET)
  public String addSubCategory(Model model) {
    ResponseMessage response = new ResponseMessage();
    AccessorySubCategory accessorySubCategory = new AccessorySubCategory();
    response.setObject(accessorySubCategory);
    model.addAttribute("response", response);
    return "admin/forms/accessory-subcategory";
  }

  @RequestMapping(value = "/sub-category/edit", method = RequestMethod.GET)
  public String editSubcategory(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    AccessorySubCategory accessorySubCategory = accessorySubCategoryService.getAccessorySubCategroryById(id);
    response.setObject(accessorySubCategory);
    model.addAttribute("response", response);
    return "admin/forms/accessory-subcategory";
  }

  @RequestMapping(value = "/sub-category/save", method = RequestMethod.POST)
  public String saveSubCategory(@ModelAttribute AccessorySubCategory accessorySubCategory,
      Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(accessorySubCategoryService.save(accessorySubCategory));
    model.addAttribute("response", response);

    return "redirect:/accessory-category/sub-category";
  }

}
