package com.r2r.road2ring.modules.transactionlog;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Integer> {
  List<TransactionLog> findAllByTransactionId(int id);
}
