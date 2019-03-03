package com.r2r.road2ring.service;

import com.r2r.road2ring.model.Account;
import com.r2r.road2ring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by paulms on 7/5/2017.
 */
@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  public void register(Account account) {
    account.setRole("ROLE_SURVEYER");
    accountRepository.save(account);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username);

    if (account == null) {
      System.out
          .println("UsernameNotFoundException: Username not found for username =====> " + username);
      throw new UsernameNotFoundException("Mahasiswa not found");
    }

    System.out.println("===================================");
    System.out.println("Nama\t:" + account.getUsername());
    System.out.println("Role\t:" + account.getRole());
    System.out.println("===================================");

    List<GrantedAuthority> auth = AuthorityUtils
        .commaSeparatedStringToAuthorityList(account.getRole());
    String password = account.getPassword();

    return new User(username, password, auth);
  }
}
