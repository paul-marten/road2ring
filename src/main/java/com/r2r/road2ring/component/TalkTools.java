package com.r2r.road2ring.component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TalkTools {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public String hashingPassword(String password) {
    String hashedPassword = new BCryptPasswordEncoder().encode(password);

    return hashedPassword;
  }

  public String md5Encode(String pass) {
    MessageDigest alg = null;
    try {
      alg = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (alg != null) {
      alg.reset();
      alg.update(pass.getBytes());
      byte[] digest = alg.digest();

      StringBuffer hashedpasswd = new StringBuffer();
      String hx;
      for (int i = 0; i < digest.length; i++) {
        hx = Integer.toHexString(0xFF & digest[i]);
        // 0x03 is equal to 0x3, but we need 0x03 for our md5sum
        if (hx.length() == 1) {
          hx = "0" + hx;
        }
        hashedpasswd.append(hx);
      }
      return hashedpasswd.toString();
    } else
      return null;
  }
}
