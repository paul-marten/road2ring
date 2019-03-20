package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
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
  @JsonView(ResponseView.LimitedTripFacility.class)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "trip_facility_trip_id" , nullable = true)
  @JsonView(ResponseView.DetailedTripFacility.class)
  private Trip trip;

  @Column(name = "trip_facility_facility_name")
  @JsonView(ResponseView.DefaultTripFacility.class)
  private String facilityName;

  @JsonView(ResponseView.DefaultTripFacility.class)
  @Column(name = "trip_facility_facility_image")
  private String facilityImage;

  @JsonView(ResponseView.DefaultTripFacility.class)
  @Column(name = "trip_facility_is_included")
  private Boolean isIncluded;

}
