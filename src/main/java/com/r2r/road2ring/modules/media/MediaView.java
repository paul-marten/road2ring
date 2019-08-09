package com.r2r.road2ring.modules.media;

import lombok.Data;

@Data
public class MediaView {
  private Integer id;
  private String picture;
  private String title;
  private String link;
  private AlbumType type;
}
