package com.r2r.road2ring.configuration.security;

import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresClearingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class WebRoad2RoadSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService customConsumerDetailsService;

  @Autowired
  private FormLoginConfigurerEnhancer enhancer;

  @Autowired
  CustomLoginFailuresManager customLoginFailuresManager;

  @Override
  protected void configure(HttpSecurity http) throws Exception {



    enhancer.addRecaptchaSupport(http.formLogin())
        .loginPage("/login")
        .successHandler(successHandler())
        .permitAll();
    http
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
        .and()
        .antMatcher("**").authorizeRequests()
//        .antMatchers("/my-profile").hasAnyRole("CONSUMER")
//        .antMatchers("/my-profile/edit").hasAnyRole("CONSUMER")
//        .antMatchers("/my-profile/save").hasAnyRole("CONSUMER")
//        .antMatchers("/profile/edit/").hasAnyRole("CONSUMER")
//        .antMatchers("/just-for-you").hasAnyRole("CONSUMER")
        .antMatchers("/**").permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessHandler(logoutSuccessHandler())
        .permitAll()
        .and()
        .rememberMe()
        .key("A9d92pssiSIdpe39393")
        .rememberMeServices(rememberMeServices())
        .tokenValiditySeconds(Integer.MAX_VALUE)
        .and()
        .csrf().disable();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(customConsumerDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    LoginFailuresClearingHandler handler = new LoginFailuresClearingHandler(customLoginFailuresManager);
    handler.setUseReferer(true);
    return handler;
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
    handler.setUseReferer(true);
    return handler;
  }

  @Bean
  public RememberMeServices rememberMeServices() {

    TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("A9d92pssiSIdpe39393", customConsumerDetailsService);

    // See http://stackoverflow.com/questions/25565809/implementing-a-remember-me-for-spring-social
    rememberMeServices.setAlwaysRemember(true);

    return rememberMeServices;

  }

}
