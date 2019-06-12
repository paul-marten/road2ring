package com.r2r.road2ring.modules.headline;

import com.r2r.road2ring.modules.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/headline")
public class HeadlineController {

  HeadlineService headlineService;

  @Autowired
  public void setHeadlineService(HeadlineService headlineService) {
    this.headlineService = headlineService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model){
    return "admin/page/headline";
  }
}
