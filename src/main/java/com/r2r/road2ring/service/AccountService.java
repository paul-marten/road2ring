package com.r2r.road2ring.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.r2r.road2ring.modules.Consumer.Consumer;
import com.r2r.road2ring.repositories.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * Created by paulms on 7/5/2017.
 */
@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public DataTablesOutput<Consumer> findAllSurveyer(@Valid DataTablesInput input) {
    return accountRepository.findAll(input);
  }

  public Consumer findAccountByUsername(String username) {
    return accountRepository.findByUsername(username);
  }

  public Consumer saveUser(Consumer account) {
    Consumer result = new Consumer();
    if (account.getId() == 0) {
      String password = new BCryptPasswordEncoder().encode(account.getPlainPassword());
      result.setPassword(password);
      result.setTotalField(0);
      result.setRole("ROLE_SURVEYER");
      result.setName(account.getName());
      result.setUsername(account.getUsername());
      result.setPhone(account.getPhone());
    } else {
      result = accountRepository.findOne(account.getId());
      result.setName(account.getName());
      result.setUsername(account.getUsername());
      result.setPhone(account.getPhone());
    }
    return accountRepository.save(result);
  }

  public Consumer findAccountById(int id) {
    return accountRepository.findOne(id);
  }
}
