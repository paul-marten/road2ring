package com.r2r.road2ring.modules.transaction;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class TransactionalDetailView extends TransactionView{

  private Integer id;
  private Date created;
  private String notes;
  private Integer price;
  private Date finishDate;
  private String meetingPoint;
  private List<TransactionDetailAccessoryView> accessoryViews;

}
