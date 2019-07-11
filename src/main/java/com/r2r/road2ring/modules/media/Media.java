package com.r2r.road2ring.modules.media;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.album.Album;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.gallery.Gallery;
import java.io.Serializable;
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
public class Media implements Serializable {

  private static final long serialVersionUID = -8033390519471976372L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "media_id")
  @JsonView(ResponseView.DefaultMedia.class)
  private Integer id;

  @Column(name = "media_picture_url")
  @JsonView(ResponseView.DefaultMedia.class)
  private String picture;

  @Column(name = "media_title")
  @JsonView(ResponseView.DefaultMedia.class)
  private String title;

  @Column(name = "media_link")
  @JsonView(ResponseView.DefaultMedia.class)
  private String link;

  @Column(name = "media_type")
  @JsonView(ResponseView.DefaultMedia.class)
  private AlbumType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "media_album_id", nullable = false)
  private Album album;
}
