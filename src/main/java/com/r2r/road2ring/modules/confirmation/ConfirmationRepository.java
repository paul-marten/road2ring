package com.r2r.road2ring.modules.confirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Integer> {
  public Confirmation findOneByCodeTransaction(String codeTransaction);
}
