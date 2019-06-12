package com.r2r.road2ring.modules.gallery;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  GalleryRepository galleryRepository;

  @Autowired
  public void setGalleryRepository(GalleryRepository galleryRepository) {
    this.galleryRepository = galleryRepository;
  }

  public List<Gallery> getAllGallery(){
    return galleryRepository.findAll();
  }

  public Gallery getGalleryById(int id) {
    return galleryRepository.findOne(id);
  }

  public Gallery saveTesti(Gallery gallery) {
    Gallery saved = new Gallery();

    if(gallery.getId() != null && gallery.getId() != 0){
      saved = galleryRepository.findOne(gallery.getId());
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

    return galleryRepository.save(saved);
  }
}
