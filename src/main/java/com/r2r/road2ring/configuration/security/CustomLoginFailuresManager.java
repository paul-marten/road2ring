package com.r2r.road2ring.configuration.security;

import com.github.mkopylec.recaptcha.RecaptchaProperties;
import com.github.mkopylec.recaptcha.security.login.InMemoryLoginFailuresManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(RecaptchaProperties.class)
public class CustomLoginFailuresManager extends InMemoryLoginFailuresManager {

  public CustomLoginFailuresManager(RecaptchaProperties recaptcha) {
    super(recaptcha);
  }

}
