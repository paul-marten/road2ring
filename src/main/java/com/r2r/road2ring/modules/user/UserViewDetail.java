package com.r2r.road2ring.modules.user;

import java.util.Date;
import lombok.Data;

@Data
public class UserViewDetail extends UserView {
  private String fullName;
  private String driverLicenseNumber;
  private String driverLicensePicture;
  private UserIdentity userIdentity;
  private String userIdentityNumber;
  private String userIdentityPicture;
  private Date birthday;
}
