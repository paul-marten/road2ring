package com.r2r.road2ring.modules.transactionlog;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogService {

  TransactionLogRepository transactionLogRepository;

  @Autowired
  public void setTransactionLogRepository(TransactionLogRepository transactionLogRepository){
    this.transactionLogRepository = transactionLogRepository;
  }

  public void setTransactionLog(TransactionLog transactionLog){
    TransactionLog saved = new TransactionLog();
    saved.setCreator(transactionLog.getCreator());
    saved.setCreated(new Date());
    saved.setAction(transactionLog.getAction());
    saved.setTransactionId(transactionLog.getTransactionId());
    saved.setUsername(transactionLog.getUsername());
    transactionLogRepository.save(saved);
  }

  public List<TransactionLog> getAllLogbyTrxID(int trx_id){
    return transactionLogRepository.findAllByTransactionId(trx_id);
  }
}
