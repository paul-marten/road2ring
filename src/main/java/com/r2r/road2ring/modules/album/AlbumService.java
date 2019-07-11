package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.media.Media;
import com.r2r.road2ring.modules.media.MediaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

  AlbumRepository albumRepository;

  MediaService mediaService;

  @Autowired
  public void setAlbumRepository(AlbumRepository albumRepository){
    this.albumRepository = albumRepository;
  }

  @Autowired
  public void setMediaService(MediaService mediaService){
    this.mediaService = mediaService;
  }

  public Album getAlbumById(Integer id){
    Album album = albumRepository.findOne(id);
    return album;
  }


  public Album saveAlbum(Album album){
    System.out.println();
    System.out.println(album.toString());
    System.out.println();
    Album saved = new Album();
    if(album.getId() != null &&  album.getId() != 0){
      saved = albumRepository.findOne(album.getId());
    } else {
      saved.setCreated(new Date());
    }
    saved.setTitle(album.getTitle());
    saved.setPicture(album.getPicture());
    saved.setType(album.getType());

    Album result = albumRepository.save(saved);

    List<Media> tempListMedia = album.getListMedia();

    if (tempListMedia != null) {
      for (Media media : tempListMedia) {
        media.setAlbum(result);
        mediaService.saveMedia(media);
      }
    }

     /*List Delete Media*/
    List<Media> deletedMedias = album.getDeleteMedia();

    if (deletedMedias != null) {
      for (Media deleteMedia : deletedMedias) {
        mediaService.removeMedia(deleteMedia.getId());
      }
    }

    return result;
  }

  public List<Album> getAllAlbum() {
    return albumRepository.findAll();
  }
}
