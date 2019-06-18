package com.r2r.road2ring.modules.confirmation;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "confirmation")
@Data
public class Confirmation implements Serializable {

  private static final long serialVersionUID = -493482193006669157L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "confirmation_id")
  private Integer id;

  @Column(name = "confirmation_picture")
  private String picture;

  @Column(name = "confirmation_account_number")
  private String accountNumber;

  @Column(name = "confirmation_account_name")
  private String accountName;

  @Column(name = "confirmation_bank")
  private String bank;

  @Column(name = "confirmation_code_transaction")
  private String codeTransaction;
}
