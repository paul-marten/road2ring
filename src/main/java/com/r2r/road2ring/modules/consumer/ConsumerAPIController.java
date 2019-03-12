package com.r2r.road2ring.modules.consumer;

import com.r2r.road2ring.modules.common.Response;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{api/consumer, /m/api/consumer}")
public class ConsumerAPIController {

}
