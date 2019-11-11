package com.r2r.road2ring.modules.consumer;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ConsumerDetail extends User {

  private static final long serialVersionUID = 1L;

  private com.r2r.road2ring.modules.user.User consumer;

  public ConsumerDetail(String email,String password,Collection<? extends GrantedAuthority> auth, com.r2r.road2ring.modules.user.User consumer){

    super(email, password, auth);
    this.consumer = consumer;
  }

  public com.r2r.road2ring.modules.user.User getConsumer() {
    return consumer;
  }

  public void setConsumer(com.r2r.road2ring.modules.user.User consumer) {
    this.consumer = consumer;
  }

  @Override
  public String toString() {
    return "ConsumerDetail [consumer.role=" + consumer.getRole().getName() + " username = "+consumer.getFullName()+" email ="+consumer.getEmail()+"]";
  }


}
