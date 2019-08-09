package com.r2r.road2ring.modules.consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Integer> {
  Consumer findTopOneByEmailIgnoreCase(String email);
  Consumer findOneByEmailIgnoreCase(String email);
  Consumer findByEmail(String email);
}
