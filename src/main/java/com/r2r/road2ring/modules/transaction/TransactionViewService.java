package com.r2r.road2ring.modules.transaction;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionViewService {


  public List<TransactionView> bindListTransactionView(List<Transaction> transactions){
    List<TransactionView> transactionViews = new ArrayList<>();
    for(Transaction transaction : transactions){
      transactionViews.add(this.bindTransactionView(transaction));
    }
    return transactionViews;
  }

  public TransactionView bindTransactionView(Transaction transaction){
    TransactionView result = new TransactionView();
    result.setId(transaction.getId());
    result.setTitle(transaction.getTrip().getTitle());
    result.setDuration(transaction.getTrip().getDuration());
    result.setPicture(transaction.getTrip().getCoverLandscape());
    result.setTripStatus(transaction.getTripStatus());
    result.setPaymentStatus(transaction.getPaymentStatus());
    result.setStartDate(transaction.getStartDate());
    return result;
  }
}
