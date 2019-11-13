package com.r2r.road2ring.modules.accessorycategory;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessory_category")
public class AccessoryCategoryAPIController {

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

  @RequestMapping(value = "/data", method = RequestMethod.GET)
//  @JsonView(ResponseView..class)
  @JsonView(ResponseView.DefaultCategory.class)
  public List<AccessoryCategory> datatable(
      HttpServletRequest request) {

    return accessoryCategoryService.getAllAccessoryCategory();
  }
  @RequestMapping(value = "/sub-category/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DefaultSubCategory.class)
  public List<AccessorySubCategory> datatableSubCatrgory(
      HttpServletRequest request) {

    return accessorySubCategoryService.findAllSubCategory();
  }

  @RequestMapping(value = "/helper", method = RequestMethod.GET)
  public ResponseMessage helper(@RequestParam("keyword") String keyword){
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(accessoryCategoryService.getAutoCompleteCaptain(keyword));

    return responseMessage;
  }

  @PostMapping("/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusTrip(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      accessoryCategoryService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }

  @PostMapping("/sub-category/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusSubCategory(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      accessorySubCategoryService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }
}
