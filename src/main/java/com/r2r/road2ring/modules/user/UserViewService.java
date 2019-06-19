package com.r2r.road2ring.modules.user;

import org.springframework.stereotype.Component;

@Component
public class UserViewService {

  public UserView bindUserView(User user){
    UserView userView = new UserView();
    userView.setId(user.getId());
    userView.setEmail(user.getEmail());
    userView.setPicture(user.getPicture());
    return userView;
  }

  public UserViewDetail bindUserViewDetail(User user){
    UserViewDetail userViewDetail = new UserViewDetail();
    userViewDetail.setId(user.getId());
    userViewDetail.setEmail(user.getEmail());
    userViewDetail.setUserPicture(user.getPicture() != null ? user.getPicture() : "");
    userViewDetail.setDriverLicenseNumber(user.getDriverLicenseNumber() != null
        ? user.getDriverLicenseNumber().toString() : "");
    userViewDetail.setDriverlicensePicture(user.getDriverLicensePicture());
    userViewDetail.setFullName(user.getFullName());
    userViewDetail.setUserIdentity(user.getUserIdentity() != null ? user.getUserIdentity() : null);
    userViewDetail.setUseridentityPicture(user.getUserIdentityPicture());
    userViewDetail.setUserIdentityNumber(user.getUserIdentitiyNumber() != null
      ? user.getUserIdentitiyNumber().toString() : "");
    userViewDetail.setBirthday(user.getBirthday());
    userViewDetail.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
    userViewDetail.setBloodType(user.getBloodType() != null ? user.getBloodType() : "");
    return userViewDetail;
  }

}
