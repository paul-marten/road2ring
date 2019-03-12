package com.r2r.road2ring.modules.gallery;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.media.Media;
import java.io.Serializable;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "gallery")
  @OrderBy("id ASC")
  private List<Media> media;

}
