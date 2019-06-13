package com.r2r.road2ring.modules.gallery;

import lombok.Data;

@Data
public class GalleryDetailView extends GalleryView {
  private int duration;
  private int distance;
  private String description;
  private String coverPotrait;
  private String tags;
  private String article;
  private Boolean isVideo;
}
