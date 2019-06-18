package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.PaymentStatus;
import com.r2r.road2ring.modules.common.TripStatus;
import com.r2r.road2ring.modules.trip.Trip;
import com.r2r.road2ring.modules.user.User;
import java.util.Date;
import lombok.Data;

@Data
public class TransactionConfirmationDetailView {
  //transaction info
  private Integer trxId;
  private String code;
  private Date created;
  private Date updated;
  private String createdBy;
  private String updatedBy;
  private PaymentStatus paymentStatus;
  private TripStatus tripStatus;
  private Integer price;
  private User user;
  private Date startDate;
  private Date completePaymentDate;
  private Date expiredPaymentDate;
  private String notes;
  private Trip trip;
  private boolean isConfirmed;

  //confirmation info
  private Integer confirmationId;
  private String picture;
  private String accountNumber;
  private String bank;
  private String codeTrx;

}
