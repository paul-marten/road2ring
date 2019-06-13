package com.r2r.road2ring.modules.headline;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/headline")
public class HeadlineAPIController {

  HeadlineService headlineService;

  @Autowired
  public void setHeadlineService(HeadlineService headlineService) {
    this.headlineService = headlineService;
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  public List<Headline> datatable(
      HttpServletRequest request) {

    return headlineService.getAllHeadline();
  }
}
