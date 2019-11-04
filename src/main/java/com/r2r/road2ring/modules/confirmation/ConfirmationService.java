package com.r2r.road2ring.modules.confirmation;

import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.transaction.TransactionService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

  ConfirmationRepository confirmationRepository;

  TransactionService transactionService;

  @Autowired
  public void setConfirmationRepository(ConfirmationRepository confirmationRepository){
    this.confirmationRepository = confirmationRepository;
  }

  @Autowired
  public void setTransactionService(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  @Transactional
  public Confirmation saveConfirmation(Confirmation confirmation) throws Road2RingException {
    Confirmation saved = new Confirmation();
    saved.setBank(confirmation.getBank());
    saved.setAccountName(confirmation.getAccountName());
    saved.setAccountNumber(confirmation.getAccountNumber());
    saved.setCodeTransaction(confirmation.getCodeTransaction());
    saved.setPicture(confirmation.getPicture() != null ?
        confirmation.getPicture() : "");
    if(confirmationRepository.save(saved) != null){
      if(transactionService.changeStatusPayment(saved.getCodeTransaction())!=null){

      }else{
        throw new Road2RingException("error saving confirmation", 800);
      }
    } else {
      throw  new Road2RingException("error saving confirmation", 800);
    }
    return saved;
  }

  public Confirmation getByTransactionCode(String code){
    return confirmationRepository.findOneByCodeTransaction(code);
  }

}
