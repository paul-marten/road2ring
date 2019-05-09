package com.r2r.road2ring.modules.confirmation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

  ConfirmationRepository confirmationRepository;

  @Autowired
  public void setConfirmationRepository(ConfirmationRepository confirmationRepository){
    this.confirmationRepository = confirmationRepository;
  }

  public Confirmation saveConfirmation(Confirmation confirmation){
    Confirmation saved = new Confirmation();
    saved.setBank(confirmation.getBank());
    saved.setAccountName(confirmation.getAccountName());
    saved.setAccountNumber(confirmation.getAccountNumber());
    saved.setCodeTransaction(confirmation.getCodeTransaction());
    saved.setPicture(confirmation.getPicture());
    return confirmationRepository.save(saved);
  }

}
