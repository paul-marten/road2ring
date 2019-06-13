package com.r2r.road2ring.modules.gallery;

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
  public String add(Model model, HttpServletRequest request) {

    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    ResponseMessage response = new ResponseMessage();
    Gallery gallery = new Gallery();
    response.setObject(gallery);
    model.addAttribute("response", response);
    model.addAttribute("video", null);
    model.addAttribute("picture", null);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/gallery";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request) {

    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    ResponseMessage response = new ResponseMessage();
    Gallery gallery= galleryService.getGalleryById(id);

    String video = "";
    String picture = "";

    if(gallery.isVideo()){
      video = gallery.getCoverLandscape();
    }else{
      picture = gallery.getCoverLandscape();
    }

    response.setObject(gallery);
    model.addAttribute("response", response);
    model.addAttribute("video", video);
    model.addAttribute("picture", picture);
    model.addAttribute("baseUrl", baseUrl);
    return "admin/forms/gallery";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Gallery gallery, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(galleryService.saveTesti(gallery));
    model.addAttribute("response", response);

    return "redirect:/gallery";
  }
}
