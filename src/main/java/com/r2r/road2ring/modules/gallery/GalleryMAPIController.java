package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.ResponseMessage;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.r2r.road2ring.modules.common.Static.GALLERY;
import static com.r2r.road2ring.modules.common.Static.M_API;

@RestController
@RequestMapping(value = M_API + GALLERY)
public class GalleryMAPIController {


  GalleryService galleryService;

  @Autowired
  GalleryViewService galleryViewService;

  @Autowired
  public void setGalleryService(GalleryService galleryService){
    this.galleryService = galleryService;
  }

  @GetMapping("/all-galleries/{pageId}/{limit}")
  public ResponseMessage getAllMyGallery(
      @PathVariable(value = "pageId") Integer pageId,
      @PathVariable(value = "limit") Integer limit,
      HttpServletResponse httpStatus) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setCode(200);

    Page<Gallery> galleries  = galleryService.getAllGallery(pageId, limit);

    responseMessage.setObject(galleryViewService.bindListGallery(galleries.getContent()));
    responseMessage.setTotalPage(galleries.getTotalPages());

    httpStatus.setStatus(HttpStatus.OK.value());

    return responseMessage;
  }

  @GetMapping("/detail/{galleryId}")
  public ResponseMessage getDetailGallery(
      @PathVariable(value = "galleryId") Integer galleryId
  ){
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(galleryService.getDetailGallery(galleryId));
    return responseMessage;
  }
}
