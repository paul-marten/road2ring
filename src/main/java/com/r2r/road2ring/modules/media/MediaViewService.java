package com.r2r.road2ring.modules.media;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MediaViewService {

  public MediaView bindMediaView (Media media){
    MediaView mediaView = new MediaView();
    mediaView.setId(media.getId());
    mediaView.setLink(media.getLink());
    mediaView.setPicture(media.getPicture());
    mediaView.setTitle(media.getTitle());
    mediaView.setType(media.getType());
    return mediaView;
  }

  public List<MediaView> bindListMediaView(List<Media> medias){
    List<MediaView> result = new ArrayList<>();
    for(Media media : medias){
      result.add(bindMediaView(media));
    }
    return result;
  }
}
