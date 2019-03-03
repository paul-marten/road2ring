package com.r2r.road2ring.service;

import com.r2r.road2ring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by paulms on 6/14/2017.
 */
@Service
public class SurveyerService {

  @Autowired
  private SurveyerRepository surveyerRepository;


  public Account getAccount(int idAccount) {
    return surveyerRepository.findOne(idAccount);
  }

  public void deleteActivity(int idAccount) {
    surveyerRepository.delete(idAccount);
  }

  public Account saveOrUpdateActivity(Account activity) {
    return surveyerRepository.save(activity);
  }

  public String checkAccount(String username, String password) {
    return "Sukses";
  }
}
