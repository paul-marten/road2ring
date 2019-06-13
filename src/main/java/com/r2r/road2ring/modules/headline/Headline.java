package com.r2r.road2ring.modules.headline;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="headline")
@Data
public class Headline implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "headline_id")
  private Integer id;

  @Column(name = "headline_is_video")
  private Boolean isVideo;

  @Column(name = "headline_title")
  private String title;

  @Column(name = "headline_link_url")
  private String linkUrl;

  @Column(name = "headline_media_url")
  private String mediaUrl;
}
