package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.common.BaseView;
import lombok.Data;

@Data
public class AccessoryView extends BaseView {
  private Integer price;
  private Integer discount;
  private String picture;
  private String description;
}
