package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.accessorycategory.AccessoryCategory;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.media.Media;
import com.r2r.road2ring.modules.media.MediaService;
import java.util.ArrayList;
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

public List<AlbumView> bindAlbum(List<Album> albums){
  List<AlbumView> albumViewList = new ArrayList<>();
  AlbumView saved;

  for(Album a : albums){
    saved = new AlbumView();

    saved.setTitle(a.getTitle());
    saved.setId(a.getId());

    albumViewList.add(saved);
  }

  return albumViewList;
}

  public List<Album> getAllAlbum() {
    return albumRepository.findAll();
  }

  public List<Album> getAutoCompleteAlbum(String keyword){
    return albumRepository.findTop5ByStatusAndTitleIgnoreCaseContaining(PublishedStatus.PUBLISHED, keyword);
  }

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    Album save = albumRepository.findOne(id);
    save.setStatus(statusId);
    albumRepository.save(save);
  }

  public Album bindAlbum(Album album) {

    Album saved = new Album();
    saved.setTitle(album.getTitle());
    saved.setId(album.getId());
    return saved;
  }
}
