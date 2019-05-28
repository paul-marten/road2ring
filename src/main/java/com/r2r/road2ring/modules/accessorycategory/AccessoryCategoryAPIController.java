package com.r2r.road2ring.modules.accessorycategory;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessory_category")
public class AccessoryCategoryAPIController {

  AccessoryCategoryService accessoryCategoryService;

  @Autowired
  public void setAccessoryCategoryService(AccessoryCategoryService accessoryCategoryService){
    this.accessoryCategoryService = accessoryCategoryService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
//  @JsonView(ResponseView..class)
  public List<AccessoryCategory> datatable(
      HttpServletRequest request) {

    return accessoryCategoryService.getAllAccessoryCategory();
  }

}
