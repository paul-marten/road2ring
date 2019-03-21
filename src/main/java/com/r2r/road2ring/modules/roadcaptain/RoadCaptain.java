package com.r2r.road2ring.modules.roadcaptain;

import com.r2r.road2ring.modules.consumer.Consumer;
import com.r2r.road2ring.modules.trip.Trip;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "road_captain")
@Data
public class RoadCaptain implements Serializable {

  private static final long serialVersionUID = 1779965122309011L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "road_captain_id")
  private Integer id;

//  private Consumer consumer;
  @Column(name = "road_captain_name")
  private String name;

  @Column(name = "road_captain_picture_url")
  private String pictureUrl;

  @Column(name = "road_captain_description")
  private String description;
}
