package com.r2r.road2ring.repository;

import com.r2r.road2ring.model.Account;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends DataTablesRepository<Account, Integer> {

  Account findByUsername(String username);

  Account findByRole(String role);

}
