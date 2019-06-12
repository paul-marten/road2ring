package com.r2r.road2ring.modules.headline;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadlineService {

  HeadlineRepository headlineRepository;

  @Autowired
  public void setHeadlineRepository(
      HeadlineRepository headlineRepository) {
    this.headlineRepository = headlineRepository;
  }

  public List<Headline> getAllHeadline(){
    return headlineRepository.findAll();
  }
}
