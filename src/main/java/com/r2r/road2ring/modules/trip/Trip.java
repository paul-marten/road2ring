package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.consumer.Consumer;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import com.r2r.road2ring.modules.roadcaptain.RoadCaptain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "trip")
@Data
public class Trip implements Serializable {

  private static final long serialVersionUID = 8796958319368475125L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trip_id")
  private Integer id;

  @Column(name = "trip_title")
  private String title;

  @Column(name = "trip_description")
  private String description;

  @Column(name = "trip_icon_cover")
  private String iconCover;

  @Column(name = "trip_icon_publisher")
  private String iconPublisher;

  @Column(name = "trip_duration")
  private Integer duration;

  @Column(name = "trip_distance")
  private Integer distance;

  @Column(name = "trip_terrain")
  private String terrain;

  @Column(name = "trip_max_rider")
  private Integer maxRider;

  @Column(name = "trip_tag")
  private String tag;

  @Column(name = "trip_created")
  private Date created;

  @Column(name = "trip_updated")
  private Date updated;

  @Transient
  private List<String> groupTitle;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "trip_road_captain" , nullable = true)
  private RoadCaptain roadCaptain;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
  private List<TripFacility> tripFacilities;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
  private List<Itinerary> itineraries;
//  private List<facility> facilities;
//  private List<>
//  private Iternary iternary;
}
