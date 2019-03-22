package com.r2r.road2ring.modules.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  public String uploadPicture(MultipartFile imageField){
    String name;
    String fullName;
    if (!imageField.isEmpty()) {
      Long date = new Date().getTime();
      name = imageField.getOriginalFilename();
      fullName = date + "_" + name;
      try {
        byte[] bytes = imageField.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
            new FileOutputStream(new File(fullName)));
        stream.write(bytes);
        stream.close();
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("null");
      }
      return fullName;
    } else {
      return "null";
    }
  }

}
