package com.r2r.road2ring.modules.album;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.media.AlbumType;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "album")
@Getter
@Setter
public class Album implements Serializable {

  private static final long serialVersionUID = 6466151266577375864L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "album_id")
  @JsonView(ResponseView.DefaultAlbum.class)
  private Integer id;

  @Column(name = "album_title")
  @JsonView(ResponseView.DefaultAlbum.class)
  private String title;

  @Column(name = "album_created")
  @JsonView(ResponseView.DefaultAlbum.class)
  private Date created;

  @Column(name = "album_status")
  @JsonView(ResponseView.DefaultAlbum.class)
  private PublishedStatus status;

  @Column(name = "album_picture")
  @JsonView(ResponseView.DefaultAlbum.class)
  private String picture;

  @Column(name = "album_type")
  @JsonView(ResponseView.DefaultAlbum.class)
  private AlbumType type;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "album")
  @OrderBy("id ASC")
  @JsonView(ResponseView.DetailedAlbum.class)
  private List<Media> media;

  @Transient
  private List<Media> deleteMedia;

  @Transient
  private List<Media> listMedia;
}
