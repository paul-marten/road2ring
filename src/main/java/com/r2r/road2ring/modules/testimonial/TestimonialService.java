package com.r2r.road2ring.modules.testimonial;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService {

  TestimonialRepository testimonialRepository;

  TestimonialViewService testimonialViewService;

  @Autowired
  public void setTestimonialRepository(
      TestimonialRepository testimonialRepository) {
    this.testimonialRepository = testimonialRepository;
  }

  @Autowired
  public void setTestimonialViewService(TestimonialViewService testimonialViewService) {
    this.testimonialViewService = testimonialViewService;
  }

  public Testimonial saveTesti(Testimonial testimonial) {
    Testimonial saved = new Testimonial();

    if (testimonial.getId() != null && testimonial.getId() != 0) {
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
    saved.setIsVideo(testimonial.getIsVideo());

    return testimonialRepository.save(saved);
  }

  public List<Testimonial> getAllTesti() {
    return testimonialRepository.findAll();
  }

  public Testimonial getTestiById(int id) {
    return testimonialRepository.findOne(id);
  }

  public List<TestimonialView> getAllTestimonial(int pageId, int limit) {
    Pageable pageable = new PageRequest(pageId, limit);
    List<Testimonial> testimonials = testimonialRepository.findAllByOrderByIdDesc(pageable);
    List<TestimonialView> result = testimonialViewService.bindListTestimonials(testimonials);
    return result;
  }

  public TestimonialDetailView getDetailTestimonials(int testimonialId) {
    TestimonialDetailView result = testimonialViewService
        .bindDetailTestimonial(testimonialRepository.findOne(testimonialId));
    return result;
  }

}
