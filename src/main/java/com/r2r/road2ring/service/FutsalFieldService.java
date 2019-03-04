package com.r2r.road2ring.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.r2r.road2ring.models.Account;
import com.r2r.road2ring.repositories.AccountRepository;

/**
 * Created by paulms on 7/4/2017.
 */
@Service("futsalFieldService")
public class FutsalFieldService {

  @Autowired
  private FutsalFieldRepository futsalFieldRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private DetailPriceRepository detailPriceRepository;

  public DataTablesOutput<FutsalField> findAllFutsalFieldAdmin(@Valid DataTablesInput input) {
    return futsalFieldRepository.findAll(input);
  }

  public FutsalField findFutsalFieldById(int id) {
    return futsalFieldRepository.findOne(id);
  }

  public List<FutsalFieldMap> findAllFutsalFieldMap() {
    return futsalFieldRepository.findAllByOrderByIdFutsalField();
  }

  public Page<FutsalField> findAllFutsalField(int page) {
    return futsalFieldRepository.findAllByOrderByIdFutsalFieldDesc(new PageRequest(page - 1, 10));
  }

  public void deleteFutsalField(int id) {
    FutsalField futsalFieldAccount = new FutsalField();
    Account accountSave = new Account();
    futsalFieldAccount = futsalFieldRepository.findOne(id);
    accountSave = accountRepository.findOne(futsalFieldAccount.getAccount().getId());
    int totalField = accountSave.getTotalField();
    accountSave.setTotalField(totalField - 1);
    if (accountRepository.save(accountSave) != null) {
      futsalFieldRepository.delete(id);
    }
    ;
  }

  public String saveField(FutsalField futsalField) {
    java.util.Date today = new java.util.Date();

    String message = "";

    FutsalField futsalFieldSave = new FutsalField();
    futsalFieldSave.setFieldName(futsalField.getFieldName());
    futsalFieldSave.setDetailLocation(futsalField.getDetailLocation());
    futsalFieldSave.setPhone(futsalField.getPhone());
    futsalFieldSave.setNumberOfField(futsalField.getNumberOfField());
    futsalFieldSave.setOpeningHours(futsalField.getOpeningHours());
    futsalFieldSave.setClosingHours(futsalField.getClosingHours());
    futsalFieldSave.setPrice(futsalField.getPrice());
    futsalFieldSave.setAccount(futsalField.getAccount());
    futsalFieldSave.setLocation(futsalField.getLocation());
    futsalFieldSave.setLatestUpdate(new java.sql.Timestamp(today.getTime()));
    Account accountSaveFutsalField = new Account();

//		String[] days = futsalField.getDays().split(",");
//		List<String> dayList = Arrays.asList(futsalField.getDays().split(","));
    accountSaveFutsalField = accountRepository.findOne(futsalField.getAccount().getId());
    if (futsalFieldRepository.save(futsalFieldSave) != null) {
      FutsalField futsalFieldIdAfterSave = new FutsalField();
      futsalFieldIdAfterSave = futsalFieldRepository
          .findTopByAccountOrderByIdFutsalFieldDesc(accountSaveFutsalField);
      int totalFutsalField = accountSaveFutsalField.getTotalField() + 1;
      accountSaveFutsalField.setTotalField(totalFutsalField);
      if (accountRepository.save(accountSaveFutsalField) != null) {
        String[] days = futsalField.getDays().split(",");
        for (int index = 0; index < days.length; index++) {
          DetailPrice detailPrice = new DetailPrice();
          detailPrice.setEndTime(futsalField.getClosingHours().toString());
          detailPrice.setStartTime(futsalField.getOpeningHours().toString());
          detailPrice.setPrice(futsalField.getPrice());
          detailPrice.setDay(days[index]);
          detailPrice.setFutsalField(futsalFieldIdAfterSave);
          insert(detailPrice);
        }
        message = "Success, Status 200 OK";
      } else {
        message = "Error";
      }
    } else {
      message = "Error";
    }
    return message;
  }

  @Transactional
  public void insert(DetailPrice detailPrice) {
    detailPriceRepository.save(detailPrice);
  }
}
