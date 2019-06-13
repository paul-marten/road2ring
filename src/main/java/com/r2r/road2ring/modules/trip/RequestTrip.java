package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="request_trip")
@Data
public class RequestTrip implements Serializable {

  private static final long serialVersionUID = 8630075520561403133L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "request_trip_id")
  private Integer id;

  @Column(name = "request_max_rider")
  private Integer maxRider;

  @Column(name = "request_start_date")
  private Date startDate;

  @Column(name = "request_user_email")
  private String userEmail;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "request_trip_trip_id" , nullable = true)
  @JsonView(ResponseView.DetailedTripPrice.class)
  private Trip trip;

  @Transient
  private long startTimestamp;
}
