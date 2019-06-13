package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.BaseView;
import java.util.Date;
import lombok.Data;

@Data
public class GalleryView extends BaseView {
  private String coverLandscape;
  private String iconCover;
  private Date startTripDate;
  private Date endTripDate;
}
