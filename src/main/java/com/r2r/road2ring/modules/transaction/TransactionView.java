package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.BaseView;
import com.r2r.road2ring.modules.common.PaymentStatus;
import com.r2r.road2ring.modules.common.TripStatus;
import java.util.Date;
import lombok.Data;

@Data
public class TransactionView extends BaseView {
  public String picture;
  public TripStatus tripStatus;
  public Date startDate;
  public Integer duration;
  public PaymentStatus paymentStatus;
}
