package com.r2r.road2ring.modules.roadcaptain;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
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
    }else{
      saved.setStatus(PublishedStatus.UNPUBLISHED);
    }

    saved.setName(captain.getName());
    saved.setDescription(captain.getDescription());
    saved.setPictureUrl(captain.getPictureUrl());

    return roadCaptainRepository.save(saved);
  }
  public List<RoadCaptain> getAutoCompleteCaptain(String keyword){
//    return roadCaptainRepository.findTop5ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
    return roadCaptainRepository.findTop5ByStatusAndNameIgnoreCaseContaining(PublishedStatus.PUBLISHED, keyword);
  }

  public RoadCaptain getCaptainById(int id){
    return  roadCaptainRepository.findOne(id);
  }

  public List<RoadCaptain> getAllCaptain(){
    return roadCaptainRepository.findAll();
  }

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    RoadCaptain save = roadCaptainRepository.findOne(id);
    save.setStatus(statusId);
    roadCaptainRepository.save(save);
  }
}
