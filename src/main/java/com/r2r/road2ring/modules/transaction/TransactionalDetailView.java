package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.motor.Motor;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class TransactionalDetailView extends TransactionView{

  private Integer id;
  private Date created;
  private String notes;
  private Integer price;
  private String code;
  private Date finishDate;
  private String meetingPoint;
  private List<TransactionDetailAccessoryView> accessories;
  private Motor motor;

}
