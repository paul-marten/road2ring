package com.r2r.road2ring.modules.common;

public class Road2RingException extends Exception {
  int code;

  public Road2RingException(String message,int code){
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
