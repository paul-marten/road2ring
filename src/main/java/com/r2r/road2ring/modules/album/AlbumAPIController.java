package com.r2r.road2ring.modules.album;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
  @JsonView(ResponseView.DetailedAlbum.class)
  public List<Album> datatable(
      HttpServletRequest request) {

    return albumService.getAllAlbum();
  }

  @RequestMapping(value = "/helper", method = RequestMethod.GET)
  public ResponseMessage helperPublished(@RequestParam("keyword") String keyword) {

    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(albumService.bindAlbum(albumService.getAutoCompleteAlbum(keyword)));

    return responseMessage;

  }

  @PostMapping("/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusTrip(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      albumService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }

  @GetMapping("/get/{id}")
  public ResponseMessage getAlbum(@PathVariable("id") int id, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(albumService.bindAlbum(albumService.getAlbumById(id)));

    return responseMessage;
  }

}
