package com.r2r.road2ring.modules.testimonial;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/testimonial")
public class TestimonialAPIController {

  TestimonialService testimonialService;

  @Autowired
  public void setTestimonialService(
      TestimonialService testimonialService) {
    this.testimonialService = testimonialService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
//  @JsonView(ResponseView..class)
  public List<Testimonial> datatable(
      HttpServletRequest request) {

    return testimonialService.getAllTesti();
  }

}
