package com.r2r.road2ring.modules.headline;

import com.r2r.road2ring.modules.common.ResponseMessage;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/headline")
public class HeadlineController {

  HeadlineService headlineService;

  @Autowired
  public void setHeadlineService(HeadlineService headlineService) {
    this.headlineService = headlineService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model, HttpServletRequest request){
    ResponseMessage response = new ResponseMessage();

    model.addAttribute("response", response);

    return "admin/page/headline";
  }



  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model, HttpServletRequest request){
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    ResponseMessage response = new ResponseMessage();

    Headline headline = new Headline();
    response.setObject(headline);

    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);

    return "admin/form/headline";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, @RequestParam int id, HttpServletRequest request){

    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    ResponseMessage response = new ResponseMessage();

    Headline headline = headlineService.getHeadlineById(id);

    System.out.println();
    System.out.println(headline.toString());
    System.out.println();

    response.setObject(headline);
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);

    return "admin/forms/headline";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Model model, @ModelAttribute Headline headline, Principal principal, HttpServletRequest request){
    String baseUrl = request.getRequestURL().toString()
        .replace(request.getRequestURI().substring(1), request.getContextPath());

    ResponseMessage response = new ResponseMessage();
    response.setObject(headlineService.saveHeadline(headline));
    model.addAttribute("response", response);
    model.addAttribute("baseUrl", baseUrl);

    return "redirect:/headline";
  }
}
