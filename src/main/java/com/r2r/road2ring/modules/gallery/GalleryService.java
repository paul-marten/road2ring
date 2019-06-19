package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  GalleryRepository galleryRepository;

  GalleryViewService galleryViewService;

  @Autowired
  public void setGalleryRepository(GalleryRepository galleryRepository) {
    this.galleryRepository = galleryRepository;
  }

  @Autowired
  public void setGalleryViewService(GalleryViewService galleryViewService){
    this.galleryViewService = galleryViewService;
  }

  public List<Gallery> getAllGallery(){
    return galleryRepository.findAll();
  }

  public Gallery getGalleryById(int id) {
    return galleryRepository.findOne(id);
  }

  public Gallery saveGallery(Gallery gallery) {
    Gallery saved = new Gallery();

    if(gallery.getId() != null && gallery.getId() != 0){
      saved = galleryRepository.findOne(gallery.getId());
    }else{
      saved.setPublishedStatus(PublishedStatus.UNPUBLISHED);
    }

    saved.setArticle(gallery.getArticle());
    saved.setCoverLandscape(gallery.getCoverLandscape());
    saved.setCoverPotrait(gallery.getCoverPotrait());
    saved.setDescription(gallery.getDescription());
    saved.setIconCover(gallery.getIconCover());
    saved.setTag(gallery.getTag());
    saved.setTitle(gallery.getTitle());
    saved.setTripDate(gallery.getTripDate());
    saved.setDistance(gallery.getDistance());
    saved.setDuration(gallery.getDuration());
    saved.setIsVideo(gallery.getIsVideo() != null ? gallery.getIsVideo() : false);

    return galleryRepository.save(saved);
  }

  public List<GalleryView> getAllGallery(int pageId, int limit){
    Pageable pageable = new PageRequest(pageId, limit);
    List<Gallery> galleries = galleryRepository.findAllByPublishedStatusOrderByIdDesc(pageable, PublishedStatus.PUBLISHED);
    List<GalleryView> result = galleryViewService.bindListGallery(galleries);
    return result;
  }

  public GalleryDetailView getDetailGallery(int galleryId){
    GalleryDetailView result = galleryViewService.bindDetailGallery(galleryRepository.findOne(galleryId));
    return result;
  }

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    Gallery gallery = galleryRepository.findOne(id);
    gallery.setPublishedStatus(statusId);
    galleryRepository.save(gallery);
  }
}
