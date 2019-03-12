package com.r2r.road2ring.modules.media;

import com.r2r.road2ring.modules.gallery.Gallery;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="media")
@Data
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "media_id")
  private Integer id;

  @Column(name = "media_url")
  private String name;

  @Column(name = "media_title")
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "media_gallery_id", nullable = false)
  private Gallery gallery;
}
