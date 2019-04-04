package com.r2r.road2ring.modules.user;

import static com.r2r.road2ring.modules.common.Static.API;
import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.USER;

import com.r2r.road2ring.modules.common.ResponseMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + USER)
public class UserAPIController {

  UserService userService;

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  @RequestMapping(value = "/auth/basic", method = RequestMethod.POST)
  public ResponseMessage login(
      @RequestHeader(value = "X-User-Agent", required = false) String userAgent,
      @ModelAttribute User user, HttpServletRequest request) {

    ResponseMessage responseMessage = new ResponseMessage();
    try {
      responseMessage.setObject(userService.login(user, userAgent));
    } catch (Exception e) {
      responseMessage.setCode(800);
      responseMessage.setMessage("Email atau Password anda salah");
    }

    return responseMessage;

  }

}
