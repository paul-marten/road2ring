package com.r2r.road2ring.modules.itinerary;

import com.r2r.road2ring.modules.trip.Trip;
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
@Table(name = "itinerary")
@Data
public class Itinerary implements Serializable {

  private static final long serialVersionUID = 8572301472549756664L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "itinerary_id")
  private Integer id;

  @Column(name = "itinerary_title")
  private String title;

  @Column(name = "itinerary_image_url")
  private String imageUrl;

  @Column(name = "itinerary_description")
  private String description;

  @Column(name = "itinerary_group")
  private Integer group;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "itinerary_trip_id" , nullable = true)
  private Trip trip;
}
