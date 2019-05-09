package com.r2r.road2ring.modules.transactionlog;

import com.r2r.road2ring.modules.transaction.Transaction;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transaction_log")
@Data
public class TransactionLog implements Serializable {

  private static final long serialVersionUID = -719377835545457659L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_log_id")
  private Integer id;

  @Column(name = "transaction_log_transaction_id")
  private Integer transactionId;

  @Column(name = "transaction_log_created")
  private Date created;

  @Column(name = "transaction_log_creator")
  private TransactionCreator creator;

  @Column(name = "transaction_log_username")
  private String username;

  @Column(name = "transaction_log_action")
  private String action;


}
