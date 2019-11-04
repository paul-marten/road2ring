package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.BaseView;
import com.r2r.road2ring.modules.common.PaymentStatus;
import com.r2r.road2ring.modules.common.TripStatus;
import java.util.Date;
import lombok.Data;

@Data
public class TransactionView extends BaseView {
  public String coverLandscape;
  public String coverPortrait;
  public String iconPublisher;
  public String iconCover;
  public String location;
  public TripStatus tripStatus;
  public Date startDate;
  public Date expiredDate;
  public Integer duration;
  public Integer tripPrice;
  public String code;
  public PaymentStatus paymentStatus;
}
