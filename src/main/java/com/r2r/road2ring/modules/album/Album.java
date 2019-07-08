package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.media.Media;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "album")
@Data
public class Album implements Serializable {

  private static final long serialVersionUID = 6466151266577375864L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "album_id")
  private Integer id;

  @Column(name = "album_title")
  private String title;

  @Column(name = "album_created")
  private Date created;

  @Column(name = "album_status")
  private PublishedStatus status;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "album")
  @OrderBy("id ASC")
  private List<Media> media;

  @Transient
  private List<Media> deleteMedia;

  @Transient
  private List<Media> listMedia;
}
