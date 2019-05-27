package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView.DetailedTransaction;
import com.r2r.road2ring.modules.transactionlog.TransactionLog;
import com.r2r.road2ring.modules.transactionlog.TransactionLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {

  TransactionService transactionService;

  TransactionLogService transactionLogService;

  TransactionDetailService transactionDetailService;

  @Autowired
  public void setTransactionService(
      TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Autowired
  public void setTransactionLogService(
      TransactionLogService transactionLogService) {
    this.transactionLogService = transactionLogService;
  }

  @Autowired
  public void setTransactionDetailService(
      TransactionDetailService transactionDetailService) {
    this.transactionDetailService = transactionDetailService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/transaction";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    TransactionConfirmationDetailView transaction = transactionService.getTransactionDetailView(id);
    List<TransactionLog> log = transactionLogService.getAllLogbyTrxID(id);
    List<TransactionDetail> detail = transactionDetailService.getAllDetailByTrxId(id);
    response.setObject(transaction);
    model.addAttribute("response", response);
    model.addAttribute("log", log);
    model.addAttribute("detail", detail);
    return "admin/forms/transaction";
  }


}
