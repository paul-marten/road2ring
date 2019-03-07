//package com.r2r.road2ring.configuration.security;
//
//import static com.r2r.road2ring.configuration.security.StaticValue.SESSION_MAX_AGE;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
//@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = SESSION_MAX_AGE)
//public class HttpSessionConfig {
//
//  @Bean
//  public CookieSerializer cookieSerializer() {
//    DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//    serializer.setCookieName("JSESSIONID");
//    serializer.setCookiePath("/");
////        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
//    serializer.setCookieMaxAge(SESSION_MAX_AGE);
//    serializer.setUseHttpOnlyCookie(true);
//    return serializer;
//  }
//}
