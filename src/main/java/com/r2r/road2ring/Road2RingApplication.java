package com.r2r.road2ring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class Road2RingApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder application) {
    return application.sources(Road2RingApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Road2RingApplication.class, args);
  }

  @Bean
  PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {

    return new EmbeddedServletContainerCustomizer() {
      @Override
      public void customize(ConfigurableEmbeddedServletContainer container) {

        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/pagenotfound");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/pagenotfound");
        ErrorPage error500Page =
            new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/internalservererror");

        container.addErrorPages(error401Page, error404Page, error500Page);
      }
    };
  }
}
