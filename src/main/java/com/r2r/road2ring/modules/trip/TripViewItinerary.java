package com.r2r.road2ring.modules.trip;

import java.util.List;
import lombok.Data;

@Data
public class TripViewItinerary {
  private String groupTitle;
  private List<ItineraryDetail> details;
}

@Data
class ItineraryDetail{
  private String imageItinerary;
  private String title;
  private String description;
}

