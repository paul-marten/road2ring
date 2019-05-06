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
    userViewDetail.setPicture(user.getPicture());
    userViewDetail.setDriverLicenseNumber(user.getDriverLicenseNumber() != null
        ? user.getDriverLicenseNumber().toString() : null);
    userViewDetail.setDriverLicensePicture(user.getDriverLicensePicture());
    userViewDetail.setFullName(user.getFullName());
    userViewDetail.setUserIdentity(user.getUserIdentity() != null ? user.getUserIdentity() : null);
    userViewDetail.setUserIdentityPicture(user.getUserIdentityPicture());
    userViewDetail.setUserIdentityNumber(user.getUserIdentitiyNumber() != null
      ? user.getUserIdentitiyNumber().toString() : null);
    userViewDetail.setBirthday(user.getBirthday());
    return userViewDetail;
  }

}
