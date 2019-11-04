package com.r2r.road2ring.modules.user;

import com.r2r.road2ring.modules.role.Role;
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
@Table(name = "user_r2r")
@Data
public class User implements Serializable {

  private static final long serialVersionUID = -3018339974747543397L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "user_birthday")
  private Date birthday;

  @Column(name = "user_password")
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_role_id", nullable = false)
  private Role role;

  @Column(name = "user_email")
  private String email;

  @Column(name = "user_driving_license_number")
  private Long driverLicenseNumber;

  @Column(name = "user_driving_license_picture")
  private String driverLicensePicture;

  @Column(name = "user_register_date")
  private Date registerDate;

  @Column(name = "user_full_name")
  private String fullName;

  @Column(name = "user_picture")
  private String picture;

  @Column(name = "user_identity")
  private UserIdentity userIdentity;

  @Column(name = "user_identity_number")
  private Long userIdentitiyNumber;

  @Column(name = "user_identity_picture")
  private String userIdentityPicture;

  @Column(name = "user_phone_number")
  private String phoneNumber;

  @Column(name = "user_blood_type")
  private String bloodType;

  @Transient
  private Long userBirthday;

  @Column(name = "user_verification_code")
  private String verificationCode;

  @Column(name = "user_activation")
  private Integer activation;

  @Column(name = "user_verification_code_password")
  private String verificationCodePassword;

  @Column(name = "user_verification_code_password_last_send")
  private Date verificationCodePasswordLastSend;

  //  private String facebookPageID;
  //  private String twitterPageID;
  //  private String facebookPassword;
  //  private String twitterPassword;
}
