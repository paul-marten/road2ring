package com.r2r.road2ring.modules.user;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.USER;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.common.UploadService;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = M_API + USER)
@CrossOrigin
public class UserAPIController {

  UserService userService;

  UploadService uploadService;

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  @Autowired
  public void setUploadService(UploadService uploadService){
    this.uploadService = uploadService;
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

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ResponseMessage registerRefresh(
      @RequestHeader(value = "X-User-Agent", required = false) String userAgent,
      @ModelAttribute(value = "user") User user, HttpServletRequest request) {
    ResponseMessage response = new ResponseMessage();
    try {
      if (user.getEmail() != null) {
        userService.register(user, user);
//        response.setObject(userService.login(user,userAgent));
      } else {
        response.setCode(703);
        response.setMessage("Missing email or username parameter");
      }
    } catch (DataIntegrityViolationException e) {
      response.setCode(703);
      response.setMessage("Email or username duplicate");
    }catch (Road2RingException e) {
      response.setCode(e.getCode());
      response.setMessage(e.getMessage());
    }

    return response;
  }

}
