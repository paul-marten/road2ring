package com.r2r.road2ring.modules.testimonial;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService {

  TestimonialRepository testimonialRepository;

  @Autowired
  public void setTestimonialRepository(
      TestimonialRepository testimonialRepository) {
    this.testimonialRepository = testimonialRepository;
  }

  public Testimonial saveTesti(Testimonial testimonial){
    Testimonial saved = new Testimonial();

    if(testimonial.getId() != null && testimonial.getId() != 0){
      saved = testimonialRepository.findOne(testimonial.getId());
    }

    saved.setArticle(testimonial.getArticle());
    saved.setCoverLandscape(testimonial.getCoverLandscape());
    saved.setCoverPotrait(testimonial.getCoverPotrait());
    saved.setDescription(testimonial.getDescription());
    saved.setIconCover(testimonial.getIconCover());
    saved.setTag(testimonial.getTag());
    saved.setTitle(testimonial.getTitle());
    saved.setTripDate(testimonial.getTripDate());
    saved.setDistance(testimonial.getDistance());
    saved.setDuration(testimonial.getDuration());
    saved.setRoadCaptain(testimonial.getRoadCaptain());
    saved.setVideo(testimonial.isVideo());

    return testimonialRepository.save(saved);
  }

  public List<Testimonial> getAllTesti(){
    return testimonialRepository.findAll();
  }

  public Testimonial getTestiById(int id) { return testimonialRepository.findOne(id); }
}
