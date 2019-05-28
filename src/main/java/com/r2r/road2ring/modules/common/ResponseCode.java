package com.r2r.road2ring.modules.common;

public enum ResponseCode {
  
  SUCCESS(600),
  AUTHENTICATION_ERROR(701),
  SESSION_ERROR(702),
  VALIDATION_ERROR(703),
  UPLOAD_TYPE_ERROR(704),
  UPLOAD_SIZE_ERROR(705),
  PERMISSION_ERROR(706),
  INTERNAL_SERVER_ERROR(800),
  REQUEST_ERROR(900);
  
  private int code;

  private ResponseCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
  
}
