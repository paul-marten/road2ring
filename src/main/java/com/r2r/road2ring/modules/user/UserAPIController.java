package com.r2r.road2ring.modules.user;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.USER;

import com.r2r.road2ring.modules.common.ResponseMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

//  @RequestMapping(value = "/registration", method = RequestMethod.POST)
//  public ResponseMessage registerRefresh(
//      @RequestHeader(value = "X-User-Agent", required = false) String userAgent,
//      @RequestParam(value = "picture", required = false) MultipartFile file,
//      @ModelAttribute(value = "user") User user, HttpServletRequest request) {
//    ResponseMessage response = new ResponseMessage();
//    User userPicture = null;
//    String baseUrl = webService.getBaseUrl(request);
//
//    try {
//      if (consumer.getEmail() != null && consumer.getUsername() != null) {
//        if (file != null) {
//          consumerPicture = uploadService.uploadConsumer(file);
//        }
//        consumerService.register(consumer, consumerPicture, baseUrl);
//        response.setObject(consumerService.login(consumer,userAgent,baseUrl));
//      } else {
//        response.setCode(703);
//        response.setMessage("Missing email or username parameter");
//      }
//    } catch (DataIntegrityViolationException e) {
//      response.setCode(703);
//      response.setMessage("Email or username duplicate");
//    } catch (FileSizeLimitExceededException e) {
//      response.setCode(800);
//      response.setMessage("File too big");
//    } catch (BolalobException e) {
//      response.setCode(e.getCode());
//      response.setMessage(e.getMessage());
//    }
//
//    return response;
//  }

}
