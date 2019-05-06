package com.r2r.road2ring.modules.user;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.USER;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.common.UploadService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

  UserViewService userViewService;

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  @Autowired
  public void setUploadService(UploadService uploadService){
    this.uploadService = uploadService;
  }

  @Autowired
  public void setUserViewService(UserViewService userViewService){
    this.userViewService = userViewService;
  }

  @RequestMapping(value = "/auth/basic", method = RequestMethod.POST)
  public ResponseMessage login(
      @RequestHeader(value = "X-User-Agent", required = false) String userAgent,
      @ModelAttribute User user, HttpServletRequest request,
      HttpServletResponse httpResponse) {

    ResponseMessage responseMessage = new ResponseMessage();
    try {
      responseMessage.setObject(userService.login(user, userAgent));
      httpResponse.setStatus(HttpStatus.OK.value());
    } catch (Exception e) {
      httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
      responseMessage.setCode(800);
      responseMessage.setMessage("Email atau Password anda salah");
    }

    return responseMessage;

  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ResponseMessage registerRefresh(
      @RequestHeader(value = "X-User-Agent", required = false) String userAgent,
      @ModelAttribute(value = "user") User user, HttpServletRequest request,
      HttpServletResponse httpStatus) {
    ResponseMessage response = new ResponseMessage();
    try {
      if (user.getEmail() != null) {
        userService.register(user, user);
        httpStatus.setStatus(HttpStatus.OK.value());
      } else {
        httpStatus.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCode(703);
        response.setMessage("Missing email or username parameter");
      }
    } catch (DataIntegrityViolationException e) {
      httpStatus.setStatus(HttpStatus.CONFLICT.value());
      response.setCode(703);
      response.setMessage("Email or username duplicate");
    }catch (Road2RingException e) {
      httpStatus.setStatus(HttpStatus.CONFLICT.value());
      response.setCode(e.getCode());
      response.setMessage(e.getMessage());
    }

    return response;
  }

  @GetMapping(value = "/profile")
  public ResponseMessage getProfileUser(Principal principal,
      HttpServletResponse httpStatus){
    ResponseMessage response = new ResponseMessage();
    if(principal != null ){
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      User user = userService.findUserByEmail(currentConsumer.getUsername());
      response.setCode(200);
      response.setObject(userViewService.bindUserViewDetail(user));
      httpStatus.setStatus(HttpStatus.OK.value());
    }else {
      httpStatus.setStatus(HttpStatus.BAD_REQUEST.value());
      response.setCode(703);
      response.setMessage("Please login first");
    }
    return response;
  }

}
