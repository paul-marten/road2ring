package com.r2r.road2ring.modules.transaction;

import com.r2r.road2ring.modules.accessory.Accessory;
import com.r2r.road2ring.modules.accessory.AccessoryRepository;
import com.r2r.road2ring.modules.motor.Motor;
import com.r2r.road2ring.modules.motor.MotorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailService {

  AccessoryRepository accessoryRepository;

  TransactionDetailRepository transactionDetailRepository;

  MotorRepository motorRepository;

  @Autowired
  public void setTransactionDetailRepository(
      TransactionDetailRepository transactionDetailRepository) {
    this.transactionDetailRepository = transactionDetailRepository;
  }

  @Autowired
  public void setAccessoryRepository(AccessoryRepository accessoryRepository) {
    this.accessoryRepository = accessoryRepository;
  }

  @Autowired
  public void setMotorRepository(MotorRepository motorRepository) {
    this.motorRepository = motorRepository;
  }

  public void saveTransactionDetailAccessory(Accessory accessory, Transaction transaction) {
    TransactionDetail transactionDetail = new TransactionDetail();
    transactionDetail.setTransaction(transaction);
    transactionDetail.setTitle(accessory.getTitle());
    transactionDetail.setType(accessory.getAccessoryCategory().getTitle());
    transactionDetail.setPrice(accessory.getPrice());
    transactionDetail.setDiscount(accessory.getDiscount());
    transactionDetail.setPicture(accessory.getPicture());
    transactionDetail.setSize(accessory.getSize());
    transactionDetailRepository.save(transactionDetail);
  }

  public void saveListTransactionalAccessory(List<Accessory> accessoryList,
      Transaction transaction) {
    for (Accessory accessory : accessoryList) {
      Accessory saved = accessoryRepository.findOne(accessory.getId());
      saved.setSize(accessory.getSize());
      saveTransactionDetailAccessory(saved, transaction);
    }
  }

  public void saveMotor(Motor motor, Transaction transaction) {
    TransactionDetail transactionDetail = new TransactionDetail();
    Motor saved = motorRepository.findOne(motor.getId());
    transactionDetail.setTransaction(transaction);
    transactionDetail.setTitle(saved.getTitle());
    transactionDetail.setType("motor");
    transactionDetail.setPrice(saved.getPrice());
    transactionDetail.setDiscount(saved.getDiscount());
    transactionDetail.setPicture(saved.getPicture());
    transactionDetail.setCapacity(saved.getCapacity());
    transactionDetail.setBrand(saved.getBrand());
    transactionDetailRepository.save(transactionDetail);
  }

  public List<TransactionDetail> getAllDetailByTrxId(int id) {
    return transactionDetailRepository.findAllByTransactionIdOrderByIdDesc(id);
  }

}