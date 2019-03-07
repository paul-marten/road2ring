package com.r2r.road2ring.configuration.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(6)
@EnableWebSecurity
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService customConsumerDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
            .antMatcher("/api/**").authorizeRequests()
            .antMatchers("/api/**").permitAll()
            .and()
            .httpBasic();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customConsumerDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}
