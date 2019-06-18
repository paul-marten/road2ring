package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.BaseView;
import lombok.Data;

@Data
public class TransactionDetailAccessoryView extends BaseView {
  private String picture;
  private String size;
  private String type;
  private Integer discount;
  private Integer price;
  private String description;
}
