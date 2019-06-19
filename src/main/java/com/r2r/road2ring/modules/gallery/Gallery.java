package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="gallery")
@Data
public class Gallery implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gallery_id")
  private Integer id;

  @Column(name = "gallery_title")
  private String title;

  @Column(name = "gallery_is_video")
  private Boolean isVideo;

  @Column(name = "gallery_trip_distance")
  private Integer distance;

  @Column(name = "gallery_trip_duration")
  private Integer duration;

  @Column(name = "gallery_cover_landscape")
  private String coverLandscape;

  @Column(name = "gallery_cover_potrait")
  private String coverPotrait;

  @Column(name = "gallery_icon_cover")
  private String iconCover;

  @Column(name = "gallery_trip_date")
  private Date tripDate;

  @Column(name = "gallery_description")
  private String description;

  @Column(name = "gallery_tag")
  private String tag;

  @Column(name = "gallery_article")
  private String article;

  @Column(name = "gallery_status")
  private PublishedStatus publishedStatus;


//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "gallery")
//  @OrderBy("id ASC")
//  private List<Media> media;

}
