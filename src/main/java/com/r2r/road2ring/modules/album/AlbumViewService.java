package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.media.MediaService;
import com.r2r.road2ring.modules.media.MediaViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumViewService {

  MediaViewService mediaViewService;

  MediaService mediaService;

  @Autowired
  public void setMediaViewService(MediaViewService mediaViewService){
    this.mediaViewService = mediaViewService;
  }

  @Autowired
  public void setMediaService(MediaService mediaService){
    this.mediaService = mediaService;
  }

  public AlbumView bindViewAlbum(Album album){
    AlbumView albumView = new AlbumView();
    albumView.setId(album.getId());
    albumView.setTitle(album.getTitle());
    albumView.setMedias(
        mediaViewService.bindListMediaView(mediaService.findAllMediaByAlbumId(album.getId())));
    return albumView;
  }

}
