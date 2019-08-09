package com.r2r.road2ring.modules.album;

import static com.r2r.road2ring.modules.common.Static.ALBUM;
import static com.r2r.road2ring.modules.common.Static.M_API;

import com.r2r.road2ring.modules.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {M_API + ALBUM})
public class AlbumMAPIController {

  AlbumService albumService;

  AlbumViewService albumViewService;

  @Autowired
  public void setAlbumService(AlbumService albumService){
    this.albumService = albumService;
  }

  @Autowired
  public void setAlbumViewService(AlbumViewService albumViewService){
    this.albumViewService = albumViewService;
  }

  @GetMapping("/detail-album/{albumId}")
  public ResponseMessage getDetailAlbum(@PathVariable("albumId") int albumId){
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(albumViewService.bindViewAlbum(albumService.getAlbumById(albumId)));
    return responseMessage;
  }

}
