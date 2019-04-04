package com.r2r.road2ring.configuration.security;

import static com.r2r.road2ring.configuration.security.StaticValue.RESOURCE_ID;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@Order(0)
public class OAuthServerConfiguration {

  @Configuration
  @EnableResourceServer
  @Order(2)
  protected static class ResourceServerConfiguration extends
      ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      // @formatter:off
      http
          .antMatcher("/m/api/**").authorizeRequests()
          .antMatchers("/oauth2").permitAll()
          .antMatchers("/m/api/user**").permitAll();
      // @formatter:on
    }

  }

  @Configuration
  @EnableAuthorizationServer
  @Order(1)
  protected static class AuthorizationServerConfiguration extends
      AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
      return new JdbcTokenStore(dataSource);
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("r2rUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception {
      // @formatter:off
      endpoints.tokenStore(tokenStore())
          .authenticationManager(this.authenticationManager)
          .userDetailsService(userDetailsService);
      // @formatter:on
    }

    /*
     curl -X POST -vu bolalob-android-app:22071983 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=bolalob&username=bianca@bolalob.com&grant_type=password&scope=read%20write&client_secret=22071983&client_id=bolalob-android-app"
     * */
    @Value("${r2r.android.client.id}")
    private String androidClientId;

    @Value("${r2r.android.client.secret}")
    private String androidClientSecret;

    @Value("${r2r.ios.client.id}")
    private String ioSClientId;

    @Value("${r2r.ios.client.secret}")
    private String ioSClientSecret;

    @Value("${r2r.web.client.id}")
    private String webClientId;

    @Value("${r2r.web.client.secret}")
    private String webClientSecret;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
        throws Exception {
      // @formatter:off
      clients.inMemory()
          .withClient(androidClientId)
          .authorizedGrantTypes("password", "refresh_token")
          //.authorizedGrantTypes("password")
          .authorities("CONSUMER").scopes("read", "write")
          .resourceIds(RESOURCE_ID).secret(androidClientSecret)
          .accessTokenValiditySeconds(2592000)
          .and()
          .withClient(ioSClientId)
          .authorizedGrantTypes("password", "refresh_token")
          .authorities("CONSUMER").scopes("read", "write")
          .resourceIds(RESOURCE_ID).secret(ioSClientSecret)
          .accessTokenValiditySeconds(2592000)
          .and()
          .withClient(webClientId)
          .authorizedGrantTypes("password", "refresh_token")
          .authorities("CONSUMER").scopes("read", "write")
          .resourceIds(RESOURCE_ID).secret(webClientSecret)
          .accessTokenValiditySeconds(2592000); //still valid for next 30 days

      //clients.jdbc(dataSource)
      // @formatter:on
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
      DefaultTokenServices tokenServices = new DefaultTokenServices();
      tokenServices.setSupportRefreshToken(true);
      //tokenServices.setSupportRefreshToken(false);
      tokenServices.setTokenStore(tokenStore());
      return tokenServices;
    }

  }

}
