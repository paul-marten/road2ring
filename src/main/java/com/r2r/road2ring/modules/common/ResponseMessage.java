package com.r2r.road2ring.modules.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseMessage {
  private int code;
  private String message;
  private Object object;
}
