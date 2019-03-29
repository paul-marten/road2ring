package com.r2r.road2ring.modules.accessory;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accessory")
@Data
public class Accessory implements Serializable {

  private static final long serialVersionUID = -7526773254128628085L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "accessory_id")
  private Integer id;

  @Column(name = "accessory_title")
  private String title;

  @Column(name = "accessory_price")
  private Integer price;

  @Column(name = "accessory_discount")
  private Integer discount;

  @Column(name = "accessory_picture")
  private String picture;

  @Column(name = "accessory_description")
  private String description;


}
