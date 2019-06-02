package com.r2r.road2ring.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  User findOneByEmailIgnoreCase(String email);
  User findTopOneByEmailIgnoreCase(String email);
  User findOneByVerificationCode(String code);
}
