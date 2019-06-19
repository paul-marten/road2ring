package com.r2r.road2ring.modules.user;

import java.util.Date;
import lombok.Data;

@Data
public class UserViewDetail {
  private int id;
  private String email;
  private String fullName;
  private String driverLicenseNumber;
  private String driverlicensePicture;
  private UserIdentity userIdentity;
  private String userPicture;
  private String userIdentityNumber;
  private String useridentityPicture;
  private Date birthday;
  private String phoneNumber;
  private String bloodType;
}
