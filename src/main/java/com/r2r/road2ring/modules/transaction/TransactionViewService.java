package com.r2r.road2ring.modules.transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

  public TransactionalDetailView bindTransactionDetail(Transaction transaction, List<TransactionDetail> transactionDetails){
    TransactionalDetailView result = new TransactionalDetailView();
    result.setTripStatus(transaction.getTripStatus());
    result.setDuration(transaction.getTrip().getDuration());
    result.setStartDate(transaction.getStartDate());
    result.setFinishDate(this.getFinishTripDuration(transaction.getStartDate(),transaction.getTrip().getDuration()));
    result.setPrice(transaction.getPrice());
    result.setCreated(transaction.getCreated());
    result.setPaymentStatus(transaction.getPaymentStatus());
    result.setNotes(transaction.getNotes());
    result.setPicture(transaction.getTrip().getCoverLandscape());
    result.setMeetingPoint(transaction.getTrip().getMeetingPoint());
    result.setAccessoryViews(this.getAccessories(transactionDetails));

    return result;
  }

  private Date getFinishTripDuration(Date startDate, int duration){
    Calendar cal = Calendar.getInstance();
    cal.setTime(startDate);
    cal.add(Calendar.DAY_OF_MONTH, duration);
    Date newDate = cal.getTime();

    return newDate;
  }

  private List<TransactionDetailAccessoryView> getAccessories(List<TransactionDetail> transactionDetails){
    List<TransactionDetailAccessoryView> result = new ArrayList<>();
    for(TransactionDetail transaction : transactionDetails){
      result.add(this.bindAccessoriesDetailTransaction(transaction));
    }
    return result;
  }

  private TransactionDetailAccessoryView bindAccessoriesDetailTransaction(TransactionDetail transactionDetail){
    TransactionDetailAccessoryView result = new TransactionDetailAccessoryView();
    result.setId(transactionDetail.getId());
    result.setType(transactionDetail.getType());
    result.setTitle(transactionDetail.getTitle());
    result.setBrand(transactionDetail.getBrand());
    result.setDiscount(transactionDetail.getDiscount());
    result.setPrice(transactionDetail.getPrice());
    result.setSize(transactionDetail.getSize());
    result.setPicture(transactionDetail.getPicture());
    return result;
  }
}
