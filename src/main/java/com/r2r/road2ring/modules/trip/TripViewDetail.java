package com.r2r.road2ring.modules.trip;

import java.util.List;
import lombok.Data;

@Data
public class TripViewDetail extends TripView {
  private String coverPotrait;
  private Integer distance;
  private String terrain;
  private Integer maxRider;
  private String iconCover;
  private String description;
  private String imageRoadCaptain;
  private String roadCaptainDescription;
  private String facilityNotIncluded;
  private String meetingPoint;
  private List<TripViewItinerary> itineraries;
}
