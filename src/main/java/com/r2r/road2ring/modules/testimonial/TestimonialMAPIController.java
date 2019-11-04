package com.r2r.road2ring.modules.testimonial;

import static com.r2r.road2ring.modules.common.Static.M_API;
import static com.r2r.road2ring.modules.common.Static.TESTIMONIAL;

import com.r2r.road2ring.modules.common.ResponseMessage;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = M_API + TESTIMONIAL)
public class TestimonialMAPIController {

  TestimonialService testimonialService;

  @Autowired
  public void setTestimonialService(TestimonialService testimonialService) {
    this.testimonialService = testimonialService;
  }

  @GetMapping("/all-testimonials/{pageId}/{limit}")
  public ResponseMessage getAllMyTestimonial(
      @PathVariable(value = "pageId") Integer pageId,
      @PathVariable(value = "limit") Integer limit,
      HttpServletResponse httpStatus) {
    ResponseMessage responseMessage = new ResponseMessage();

    responseMessage.setCode(200);
    responseMessage.setObject(testimonialService.getAllTestimonial(pageId, limit));
    httpStatus.setStatus(HttpStatus.OK.value());

    return responseMessage;
  }

  @GetMapping("/detail/{testimonialId}")
  public ResponseMessage getDetailTestimonial(
      @PathVariable(value = "testimonialId") Integer testimonialId
  ) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setObject(testimonialService.getDetailTestimonials(testimonialId));

    return responseMessage;
  }
}
