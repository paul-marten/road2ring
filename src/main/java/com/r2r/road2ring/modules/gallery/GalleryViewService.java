package com.r2r.road2ring.modules.gallery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GalleryViewService {

  public List<GalleryView> bindListGallery(List<Gallery> galleries){
    List<GalleryView> result = new ArrayList<>();
    for(Gallery gallery : galleries){
      result.add(this.bindGallery(gallery));
    }
    return result;
  }

  public GalleryView bindGallery(Gallery gallery){
    GalleryView result = new GalleryView();

    Calendar cal = Calendar.getInstance();
    cal.setTime(gallery.getTripDate());
    cal.add(Calendar.DATE, gallery.getDuration());
    Date endTripDate = cal.getTime();

    result.setCoverLandscape(gallery.getCoverLandscape());
    result.setIconCover(gallery.getIconCover());
    result.setId(gallery.getId());
    result.setTitle(gallery.getTitle());
    result.setStartTripDate(gallery.getTripDate());
    result.setEndTripDate(endTripDate);
    return result;
  }

  public GalleryDetailView bindDetailGallery(Gallery gallery){
    GalleryDetailView result = new GalleryDetailView();

    Calendar cal = Calendar.getInstance();
    cal.setTime(gallery.getTripDate());
    cal.add(Calendar.DATE, gallery.getDuration());
    Date endTripDate = cal.getTime();

    result.setCoverPotrait(gallery.getCoverPotrait());
    result.setDescription(gallery.getDescription());
    result.setDistance(gallery.getDistance());
    result.setDuration(gallery.getDuration());
    result.setCoverLandscape(gallery.getCoverLandscape());
    result.setIconCover(gallery.getIconCover());
    result.setId(gallery.getId());
    result.setTitle(gallery.getTitle());
    result.setStartTripDate(gallery.getTripDate());
    result.setEndTripDate(endTripDate);
    result.setTags(gallery.getTag());
    result.setArticle(gallery.getArticle());
    result.setIsVideo(gallery.getIsVideo());
    result.setAlbumId(gallery.getAlbumId() != null ? gallery.getAlbumId() : 0);
    return result;
  }
}
