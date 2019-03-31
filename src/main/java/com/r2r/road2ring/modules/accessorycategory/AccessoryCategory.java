package com.r2r.road2ring.modules.accessorycategory;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accessory_category")
@Data
public class AccessoryCategory implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "accessory_category_id")
  private Integer id;

  @Column(name = "accessory_category_name")
  private String title;

  @Column(name = "accessory_category_image")
  private String image;

}
