package com.r2r.road2ring.modules.testimonial;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  @JsonView(ResponseView.DefaultTestimonial.class)
  public List<Testimonial> datatable(
      HttpServletRequest request) {

    return testimonialService.getAllTesti();
  }

  @PostMapping("/change-status/{id}/{statusId}")
  public ResponseMessage changeStatusTrip(@PathVariable("id") int id,
      @PathVariable("statusId") PublishedStatus statusId, Principal principal){
    ResponseMessage responseMessage = new ResponseMessage();
    try{
      testimonialService.changeStatus(statusId,id);
    } catch (Road2RingException e){
      responseMessage.setCode(e.getCode());
      responseMessage.setMessage(e.getMessage());
    }

    return responseMessage;
  }
}
