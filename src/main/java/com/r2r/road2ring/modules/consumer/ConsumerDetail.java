package com.r2r.road2ring.modules.consumer;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ConsumerDetail extends User {

  private static final long serialVersionUID = 1L;

  private Consumer consumer;

  public ConsumerDetail(String email,String password,Collection<? extends GrantedAuthority> auth, Consumer consumer){

    super(email, password, auth);
    this.consumer = consumer;
  }

  public Consumer getConsumer() {
    return consumer;
  }

  public void setConsumer(Consumer consumer) {
    this.consumer = consumer;
  }

  @Override
  public String toString() {
    return "ConsumerDetail [consumer.role=" + consumer.getRole().getName() + " username = "+consumer.getUsername()+" email ="+consumer.getEmail()+"]";
  }


}
