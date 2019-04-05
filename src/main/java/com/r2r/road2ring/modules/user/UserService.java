package com.r2r.road2ring.modules.user;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  Environment environment;

  @Autowired
  public void setEnvironment(Environment environment){
    this.environment = environment;
  }

  public AuthToken login(User user, String userAgent) {
    AuthToken authToken = new AuthToken();
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<AuthToken> response;

    String base64 = this.createBasicAuthForApplication(userAgent);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64);

    String email = user.getEmail();
    String password = user.getPassword();

    /********************* REQUEST CENTRAL BLOCK *******************************************/
    // Sent credential to central (Look at endpoint reset username in consumer controller, it has
    // correlation)

    // if you get central_id, then pass credential to internal oauth

    // otherwise reject
    /**************************** END BLOCK ************************************************/

    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("username", email);
    map.add("password", password);
    map.add("grant_type", "password");
    HttpEntity<MultiValueMap<String, String>> request =
        new HttpEntity<MultiValueMap<String, String>>(map, headers);


    response =
        restTemplate.exchange("http://localhost:"+ environment.getProperty("server.port") + "/oauth/token", HttpMethod.POST, request, AuthToken.class);

    authToken = response.getBody();

    return authToken;
  }

  private String createBasicAuthForApplication(String userAgent) {

    String plainCreds = "", base64Creds = "";
    byte[] plainCredsBytes, base64CredsBytes;

    if (userAgent != null) {
      if (userAgent.startsWith("Android"))
        plainCreds = String.format("%s:%s", environment.getProperty("r2r.android.client.id"),
            environment.getProperty("r2r.android.client.secret"));
      else if (userAgent.startsWith("IOS"))
        plainCreds = String.format("%s:%s", environment.getProperty("r2r.ios.client.id"),
            environment.getProperty("r2r.ios.client.secret"));
      else if (userAgent.startsWith("Web"))
        plainCreds = String.format("%s:%s", environment.getProperty("r2r.web.client.id"),
            environment.getProperty("r2r.web.client.secret"));
    } else {
      plainCreds = String.format("%s:%s", environment.getProperty("r2r.android.client.id"),
          environment.getProperty("r2r.android.client.secret"));
    }
    plainCredsBytes = plainCreds.getBytes();
    base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    base64Creds = new String(base64CredsBytes);

    return base64Creds;
  }

}
