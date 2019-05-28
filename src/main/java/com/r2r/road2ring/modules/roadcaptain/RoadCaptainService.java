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

  public RoadCaptain saveRoadCaptain(RoadCaptain captain){
    RoadCaptain saved = new RoadCaptain();

    if(captain.getId() != 0 && captain.getId() != null){
      saved = this.getCaptainById(captain.getId());
    }

    saved.setName(captain.getName());
    saved.setDescription(captain.getDescription());
    saved.setPictureUrl(captain.getPictureUrl());

    return roadCaptainRepository.save(saved);
  }
  public List<RoadCaptain> getAutoCompleteCaptain(String keyword){
    return roadCaptainRepository.findTop5ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
  }

  public RoadCaptain getCaptainById(int id){
    return  roadCaptainRepository.findOne(id);
  }

  public List<RoadCaptain> getAllCaptain(){
    return roadCaptainRepository.findAll();
  }
}
