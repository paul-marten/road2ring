package com.r2r.road2ring.modules.trip;

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
@Table (name = "trip_facility")
@Data
public class TripFacility {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trip_facility_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "trip_facility_trip_id" , nullable = true)
  private Trip trip;

  @Column(name = "trip_facility_facility_name")
  private String facilityName;

  @Column(name = "trip_facility_facility_image")
  private String facilityImage;

  @Column(name = "trip_facility_is_included")
  private Boolean isIncluded;

}
