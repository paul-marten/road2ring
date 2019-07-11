package com.r2r.road2ring.modules.album;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/album")
public class AlbumAPIController {
  AlbumService albumService;

  @Autowired
  public void setAlbumService(AlbumService albumService) {
    this.albumService = albumService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DefaultAlbum.class)
  public List<Album> datatable(
      HttpServletRequest request) {

    return albumService.getAllAlbum();
  }

}
