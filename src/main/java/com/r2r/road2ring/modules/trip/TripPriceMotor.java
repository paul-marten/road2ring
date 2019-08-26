package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.motor.Motor;
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
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "trip_price_motor")
@Data
public class TripPriceMotor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trip_price_motor_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "trip_price_motor_motor_id" )
  private Motor bikes;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "trip_price_motor_trip_price_id" )
  private TripPrice tripPrice;

  @Column(name = "trip_price_motor_price")
  private Integer price;

  @Column(name = "trip_price_motor_stock")
  private Integer stock;

  @CreationTimestamp
  @Column(name="trip_price_motor_created_at")
  private Date created;

  @UpdateTimestamp
  @Column(name="trip_price_motor_updated_at")
  private Date updated;
}
