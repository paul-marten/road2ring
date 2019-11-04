package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.PublishedStatus;
import lombok.Data;

@Data
public class TripItineraryDataView {
  private int id;
  private int countEvent;
  private int groupEvent;
  private String groupTitleEvent;
  private PublishedStatus status;
}
