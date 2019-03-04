package com.r2r.road2ring.configuration.security;

import com.r2r.road2ring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/", "/register").permitAll()
            .antMatchers("/js/*","/css/*","/img/*").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/admin/index")
            .permitAll()
            .and()
            .logout().permitAll();
        http.httpBasic();
    }
    /*
     * Only Needed for inmemory authentication. Remove this code after implementing the real user in database
     */
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("buddhi").password("123456").roles("USER");
	}
	 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }
}
