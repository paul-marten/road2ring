package com.r2r.road2ring.modules.testimonial;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/testimonial")
public class TestimonialController {

  TestimonialService testimonialService;

  @Autowired
  public void setTestimonialService(
      TestimonialService testimonialService) {
    this.testimonialService = testimonialService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    ResponseMessage response = new ResponseMessage();
    model.addAttribute("response", response);
    return "admin/page/testimonial";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    ResponseMessage response = new ResponseMessage();
    Testimonial testi = new Testimonial();
    response.setObject(testi);
    model.addAttribute("response", response);
    return "admin/forms/testimonial";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute Testimonial testimonial, Model model, Principal principal) {
    ResponseMessage response = new ResponseMessage();
    response.setObject(testimonialService.saveTesti(testimonial));
    model.addAttribute("response", response);

    return "redirect:/testimonial";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id) {
    ResponseMessage response = new ResponseMessage();
    Testimonial testi= testimonialService.getTestiById(id);
    response.setObject(testi);
    model.addAttribute("response", response);
    return "admin/forms/testimonial";
  }

}
