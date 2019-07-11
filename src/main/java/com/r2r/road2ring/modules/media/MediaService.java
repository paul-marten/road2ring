package com.r2r.road2ring.modules.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

  MediaRepository mediaRepository;

  @Autowired
  public void setMediaRepository(MediaRepository mediaRepository){
    this.mediaRepository = mediaRepository;
  }

  public Media saveMedia(Media media) {

    Media saved = new Media();

    if (media.getId() != null && media.getId() != 0) {
      saved = mediaRepository.findOne(media.getId());
    } else {
      saved.setAlbum(media.getAlbum());
    }

    saved.setTitle(media.getTitle());
    saved.setPicture(media.getPicture());
    saved.setType(media.getType());
    if(media.getType() == AlbumType.YOUTUBE)
      saved.setLink(media.getLink());

    return mediaRepository.save(saved);
  }

  public void removeMedia(Integer id) {
    mediaRepository.delete(id);
  }

}
