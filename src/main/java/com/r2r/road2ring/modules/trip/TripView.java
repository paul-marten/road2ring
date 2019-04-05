package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.BaseView;
import lombok.Data;

@Data
public class TripView extends BaseView {
  private String coverLandscape;
  private Integer duration;
  private String location;
}
