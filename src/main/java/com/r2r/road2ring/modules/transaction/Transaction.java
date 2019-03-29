//package com.r2r.road2ring.modules.transaction;
//
//import com.r2r.road2ring.modules.accessory.Accessory;
//import com.r2r.road2ring.modules.common.PaymentStatus;
//import com.r2r.road2ring.modules.common.TripStatus;
//import com.r2r.road2ring.modules.motor.Motor;
//import com.r2r.road2ring.modules.trip.Trip;
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "transaction")
//@Data
//public class Transaction implements Serializable {
//
//  private static final long serialVersionUID = 6639851039826587565L;
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "transaction_id")
//  private Integer id;
//
//  @Column(name = "transaction_expired_payment")
//  private Date expiredPaymentDate;
//
//  @Column(name = "transaction_complete_payment")
//  private Date completePaymentDate;
//
//  @Column(name = "transaction_notes")
//  private String notes;
//
//  @Column(name = "transaction_payment_status")
//  @Enumerated(EnumType.ORDINAL)
//  private PaymentStatus paymentStatus;
//
//  @Column(name = "transaction_trip_status")
//  @Enumerated(EnumType.ORDINAL)
//  private TripStatus tripStatus;
//
//  @Column(name = "transaction_code")
//  private String code;
//
//  @Column(name = "transaction_trip_id")
//  private Trip trip;
//
//  @Column(name = "transaction_motor_id")
//  private Motor motor;
//
//  private Accessory accessory;
//
//  private Integer price;
//
//  private Integer totalPrice;
//}
