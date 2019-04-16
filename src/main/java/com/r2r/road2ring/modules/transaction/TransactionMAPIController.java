package com.r2r.road2ring.modules.transaction;

import static com.r2r.road2ring.modules.common.Static.M_API;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.user.User;
import com.r2r.road2ring.modules.user.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API)
public class TransactionMAPIController {

  TransactionService transactionService;

  UserService userService;

  @Autowired
  public void setTransactionService(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }


  @PostMapping("/transaction/create")
  public ResponseMessage createTransaction(
      @ModelAttribute Transaction transaction,Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      User user = userService.findUserByEmail(currentConsumer.getUsername());
      transactionService.createTransaction(transaction, user);
      responseMessage.setCode(200);
    }
    return responseMessage;
  }

  @GetMapping("/transaction/get-my-transaction/{pageId}/{limit}")
  public ResponseMessage getAllMyTransaction(
      @PathVariable(value = "pageId") Integer pageId,
      @PathVariable(value = "limit") Integer limit ,Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    if (principal != null) {
      Authentication auth = (Authentication) principal;
      UserDetails currentConsumer = (UserDetails) auth.getPrincipal();
      User user = userService.findUserByEmail(currentConsumer.getUsername());
      responseMessage.setCode(200);
      responseMessage.setObject(transactionService.getAllMyTransaction(user,pageId,limit));
    }
    return responseMessage;
  }

}
