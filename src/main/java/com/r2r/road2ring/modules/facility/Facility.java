package com.r2r.road2ring.modules.facility;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "facility")
@Data
public class Facility implements Serializable {

  private static final long serialVersionUID = -8171586653623393804L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "facility_id")
  private Integer id;

  @Column(name = "facility_title")
  private String name;

  @Column(name = "facility_image")
  private String image;
}
