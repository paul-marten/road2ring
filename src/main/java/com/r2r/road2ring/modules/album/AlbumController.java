package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.media.Media;
import java.security.Principal;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/album")
public class AlbumController {

  AlbumService albumService;

  @Autowired
  public void setAlbumService(AlbumService albumService) {
    this.albumService = albumService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/album";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Album album= new Album();
    response.setObject(album);
    model.addAttribute("response", response);
    return "admin/forms/album";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    Album album= albumService.getAlbumById(id);
    response.setObject(album);
    model.addAttribute("response", response);
    return "admin/forms/album";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Album album, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();


    if(album.getDeleteMedia()!= null) {
      for (Iterator<Media> iter = album.getDeleteMedia().listIterator();
          iter.hasNext(); ) {
        Media deleted = iter.next();
        if (deleted.getId() == 0) {
          iter.remove();
        }
      }
    }

    response.setObject(albumService.saveAlbum(album));
    model.addAttribute("response", response);
    return "redirect:/album";
  }
}
