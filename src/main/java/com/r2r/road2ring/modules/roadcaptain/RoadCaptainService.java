package com.r2r.road2ring.modules.roadcaptain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadCaptainService {

  RoadCaptainRepository roadCaptainRepository;

  @Autowired
  public void setRoadCaptainRepository(
      RoadCaptainRepository roadCaptainRepository) {
    this.roadCaptainRepository = roadCaptainRepository;
  }

  public List<RoadCaptain> getAllCaptain(){
    return roadCaptainRepository.findAll();
  }
}
