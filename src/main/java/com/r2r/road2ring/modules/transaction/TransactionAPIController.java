package com.r2r.road2ring.modules.transaction;

import static com.r2r.road2ring.modules.common.Static.API;
import static com.r2r.road2ring.modules.common.Static.TRANSACTION;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.consumer.Consumer;
import com.r2r.road2ring.modules.consumer.ConsumerService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = API + TRANSACTION)
public class TransactionAPIController {

  TransactionService transactionService;

  ConsumerService consumerService;

  @Autowired
  public void setTransactionService(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  @Autowired
  public void setConsumerService(ConsumerService consumerService){
    this.consumerService = consumerService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTransaction.class)
  public List<Transaction> datatable(
      HttpServletRequest request) {
    return transactionService.getAllTranscation();
  }
  @PostMapping("/accept-payment")
  public ResponseMessage acceptPayment(
      @ModelAttribute Transaction transaction, Principal principal){

    Authentication auth = (Authentication) principal;
    Consumer consumer = consumerService.getUserByEmail(auth.getName());
    ResponseMessage responseMessage = new ResponseMessage();
    transactionService.acceptPayment(transaction, consumer);
    return responseMessage;
  }

  @GetMapping("/check-payment-time")
  public void createTransaction(){
    transactionService.changeStatusLatePayment();
  }



}
