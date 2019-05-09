package com.r2r.road2ring.modules.testimonial;

import com.r2r.road2ring.modules.gallery.Gallery;
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
@Table(name = "testimonial")
@Data
public class Testimonial implements Serializable {

  private static final long serialVersionUID = -7657630545857549758L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "testimonial_id")
  private Integer id;

  @Column(name = "testimonial_title")
  private String title;

  @Column(name = "testimonial_cover_landscape")
  private String coverLandscape;

  @Column(name = "testimonial_cover_potrait")
  private String coverPotrait;

  @Column(name = "testimonial_icon_cover")
  private String iconCover;

  @Column(name = "testimonial_trip_date")
  private Date tripDate;

  @Column(name = "testimonial_description")
  private String description;

  @Column(name = "testimonial_tag")
  private String tag;

  @Column(name = "testimonial_article")
  private String article;

//  private Gallery gallery;
}
