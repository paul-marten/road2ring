package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

  GalleryService galleryService;

  @Autowired
  public void setGalleryService(GalleryService galleryService) {
    this.galleryService = galleryService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/gallery";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Gallery gallery = new Gallery();
    response.setObject(gallery);
    model.addAttribute("response", response);
    return "admin/forms/testimonial";
  }
}
