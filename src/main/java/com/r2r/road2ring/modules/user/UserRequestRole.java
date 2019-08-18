package com.r2r.road2ring.modules.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "user_request_rc")
@Data
public class UserRequestRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_request_rc_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_request_rc_user_id", nullable = false)
  private User user;

  @Column(name="user_request_rc_status")
  private Boolean status;

  @CreationTimestamp
  @Column(name="user_request_rc_created_at")
  private Date created;
  @UpdateTimestamp
  @Column(name="user_request_rc_updated_at")
  private Date updated;



}
