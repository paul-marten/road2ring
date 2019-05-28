package com.r2r.road2ring.modules.motor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "motor")
@Data
public class Motor implements Serializable {

  private static final long serialVersionUID = -151788449453504012L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "motor_id")
  private Integer id;

  @Column(name = "motor_title")
  private String title;

  @Column(name = "motor_capacity")
  private Integer capacity;

  @Column(name = "motor_brand")
  private String brand;

  @Column(name = "motor_price")
  private Integer price;

  @Column(name = "motor_discount")
  private Integer discount;

  @Column(name = "motor_description")
  private String description;

  @Column(name = "motor_picture")
  private String picture;
}
