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

  public Headline saveHeadline(Headline headline){
    Headline saved = new Headline();
    if(headline.getId() != null && headline.getId()!= 0){
      saved = headlineRepository.findOne(headline.getId());
    }
    saved.setLinkUrl(headline.getLinkUrl());
    saved.setMediaUrl(headline.getMediaUrl());
    saved.setTitle(headline.getTitle());
    saved.setSubtitle(headline.getSubtitle());
    saved.setIsVideo(headline.getIsVideo() != null ? headline.getIsVideo() : false);

    return headlineRepository.save(saved);
  }

  public Headline getHeadlineById(int id){
    return headlineRepository.findOne(id);
  }
}
