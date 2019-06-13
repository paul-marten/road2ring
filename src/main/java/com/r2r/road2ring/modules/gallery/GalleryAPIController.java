package com.r2r.road2ring.modules.gallery;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/gallery")
public class GalleryAPIController {

  GalleryService galleryService;

  @Autowired
  public void setGalleryService(GalleryService galleryService) {
    this.galleryService = galleryService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
//  @JsonView(ResponseView..class)
  public List<Gallery> datatable(
      HttpServletRequest request) {

    return galleryService.getAllGallery();
  }
}
