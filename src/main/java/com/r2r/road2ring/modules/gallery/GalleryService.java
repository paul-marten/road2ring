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
}
