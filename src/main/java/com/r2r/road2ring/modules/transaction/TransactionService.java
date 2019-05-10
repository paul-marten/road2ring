package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.common.PaymentStatus;
import com.r2r.road2ring.modules.common.R2rTools;
import com.r2r.road2ring.modules.common.TripStatus;
import com.r2r.road2ring.modules.consumer.Consumer;
import com.r2r.road2ring.modules.transactionlog.TransactionCreator;
import com.r2r.road2ring.modules.transactionlog.TransactionLog;
import com.r2r.road2ring.modules.transactionlog.TransactionLogService;
import com.r2r.road2ring.modules.trip.TripPriceService;
import com.r2r.road2ring.modules.user.User;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  TransactionRepository transactionRepository;

  R2rTools r2rTools;

  TripPriceService tripPriceService;

  TransactionDetailService transactionDetailService;

  TransactionViewService transactionViewService;

  TransactionDetailRepository transactionDetailRepository;

  TransactionLogService transactionLogService;

  @Autowired
  public void setTransactionRepository(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }

  @Autowired
  public void setR2rTools(R2rTools r2rTools){
    this.r2rTools = r2rTools;
  }

  @Autowired
  public void setTripPriceService(TripPriceService tripPriceService){
    this.tripPriceService = tripPriceService;
  }

  @Autowired
  public void setTransactionDetailService(TransactionDetailService transactionDetailService){
    this.transactionDetailService = transactionDetailService;
  }

  @Autowired
  public void setTransactionViewService(TransactionViewService transactionViewService){
    this.transactionViewService = transactionViewService;
  }

  @Autowired
  public void setTransactionDetailRepository(TransactionDetailRepository transactionDetailRepository){
    this.transactionDetailRepository = transactionDetailRepository;
  }

  @Autowired
  public void setTransactionLogService(TransactionLogService transactionLogService){
    this.transactionLogService = transactionLogService;
  }

  public TransactionCreateView createTransaction(Transaction transaction, User user){
    Transaction result = new Transaction();
    Date created  = new Date();

    Calendar cal = Calendar.getInstance();
    cal.setTime(created);
    cal.add(Calendar.HOUR_OF_DAY, 1);
    Date newDate = cal.getTime();

    Timestamp code = new Timestamp(created.getTime());

    Date startDate = new Date(transaction.getStartTimestamp());


    result.setUser(user);
    result.setPaymentStatus(PaymentStatus.WAITING);
    result.setTrip(transaction.getTrip());
    result.setCreated(created);
    result.setExpiredPaymentDate(newDate);
    result.setTripStatus(TripStatus.READY);
    result.setNotes(transaction.getNotes());
    result.setPrice(transaction.getPrice());
    result.setStartDate(startDate);
    result.setCode(r2rTools.generateRandomCode(2)+ code.getTime() + r2rTools.generateRandomCode(3));

    if(transactionRepository.save(result)!=null){
      tripPriceService.addPersonTripPrice(transaction.getTrip().getId(),result.getStartDate());
      Transaction transactionSaved = transactionRepository.findOneByCode(result.getCode());
      transactionDetailService.saveMotor(transaction.getMotor(),transactionSaved);
      transactionDetailService.saveListTransactionalAccessory(transaction.getAccessories(),transactionSaved);
      this.createTransactionLogByUser(transactionSaved);
    }

    TransactionCreateView view = new TransactionCreateView();
    view.setLastPayment(newDate);
    view.setTotalPrice(result.getPrice());
    view.setTransactionCodeId(result.getCode());
    return view;

  }

  @Async
  private void createTransactionLogByUser(Transaction transaction){
    TransactionLog saved = new TransactionLog();
    saved.setUsername(transaction.getUser().getEmail());
    saved.setAction("CREATED TRANSACTION BY USER");
    saved.setTransactionId(transaction.getId());
    saved.setCreator(TransactionCreator.USER);
    transactionLogService.setTransactionLog(saved);
  }

  @Async
  private void createTransactionLogBySystem(Transaction transaction){
    TransactionLog saved = new TransactionLog();
    saved.setUsername(transaction.getUser().getEmail());
    saved.setAction("CHANGE STATUS TRANSACTION TO FAILED");
    saved.setTransactionId(transaction.getId());
    saved.setCreator(TransactionCreator.SYSTEM);
    transactionLogService.setTransactionLog(saved);
  }

  @Async
  private void acceptTransactionPayment(Transaction transaction, Consumer consumer){
    TransactionLog saved = new TransactionLog();
    saved.setUsername(consumer.getEmail());
    saved.setTransactionId(transaction.getId());
    saved.setCreator(TransactionCreator.ADMIN);
    saved.setAction("ACCEPT PAYMENT BY ADMIN");
    transactionLogService.setTransactionLog(saved);
  }

  public void acceptPayment(Transaction transaction, Consumer consumer){
    Transaction saved  = transactionRepository.findOneByCode(transaction.getCode());
    saved.setPaymentStatus(PaymentStatus.PAID);
    saved.setCompletePaymentDate(new Date());
    this.acceptTransactionPayment(saved, consumer);
    transactionRepository.save(saved);
  }

  public void changeStatusLatePayment(){
    List<Transaction> transactions = transactionRepository.
        findAllByPaymentStatusAndExpiredPaymentDateLessThan(PaymentStatus.WAITING, new Date());
    for(Transaction transaction : transactions){
      transaction.setPaymentStatus(PaymentStatus.FAILED);
      transactionRepository.save(transaction);
      tripPriceService.minPersonTripPrice(transaction.getTrip().getId(), transaction.getStartDate());
      this.createTransactionLogBySystem(transaction);
    }
  }

  public List<TransactionView> getAllMyTransaction(User user, int pageId, int limit){
    Pageable pageable = new PageRequest(pageId, limit);
    List<Transaction> transactions = transactionRepository.findAllByUserIdOrderByCreatedDesc(user.getId(),pageable);
    List<TransactionView> result = transactionViewService.bindListTransactionView(transactions);
    return result;
  }

  public TransactionalDetailView getMyTransactionDetail(User user, int transactionId){
    TransactionalDetailView result;
    Transaction transaction = transactionRepository.findOneByIdAndUserId(transactionId, user.getId());
    List<TransactionDetail> transactionDetails = transactionDetailRepository.findAllByTransactionIdOrderByIdDesc(transactionId);
    result = transactionViewService.bindTransactionDetail(transaction,transactionDetails);
    return result;
  }

  public TransactionalDetailView getTransactionDetailByCode(String transactionCodeId){
    TransactionalDetailView result;
    Transaction transaction = transactionRepository.findOneByCode(transactionCodeId);
    List<TransactionDetail> transactionDetails = transactionDetailRepository.findAllByTransactionIdOrderByIdDesc(transaction.getId());
    result = transactionViewService.bindTransactionDetail(transaction,transactionDetails);
    return result;
  }

}
