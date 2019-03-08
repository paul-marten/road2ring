package com.r2r.road2ring.modules.consumer;

import com.r2r.road2ring.modules.role.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("customConsumerDetailsService")
public class CustomConsumerDetailsService implements UserDetailsService {

  private ConsumerRepository consumerRepository;

  @Autowired
  public void setConsumerRepository(ConsumerRepository consumerRepository){
    this.consumerRepository = consumerRepository;
  }

  @Transactional(readOnly=true)
  @Override
  public UserDetails loadUserByUsername(final String email)
      throws UsernameNotFoundException {
    Consumer consumer = consumerRepository.findTopOneByEmailIgnoreCase(email);
    if(consumer.getRole().getName() == null){
      Role role = new Role();
      role.setName("ROLE_CONSUMER");
      consumer.getRole().setName(role.getName());
    }
    List<GrantedAuthority> authorities = buildUserAuthority(consumer.getRole());
    return buildUserForAuthentication(consumer, authorities) ;

  }

  public ConsumerDetail loadUserByConsumer(Consumer consumer)
      throws UsernameNotFoundException {


    List<GrantedAuthority> authorities = buildUserAuthority(consumer.getRole());
    return buildUserForAuthentication(consumer, authorities) ;

  }

  private ConsumerDetail buildUserForAuthentication(Consumer consumer,
      List<GrantedAuthority> authorities) {
    return new ConsumerDetail(consumer.getEmail(), consumer.getPassword(), authorities, consumer);
  }

  private List<GrantedAuthority> buildUserAuthority(Role userRole) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    // Build user's authorities
    setAuths.add(new SimpleGrantedAuthority(userRole.getName()));

    return new ArrayList<GrantedAuthority>(setAuths);
  }
}
