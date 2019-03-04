package com.r2r.road2ring.repositories;

import com.r2r.road2ring.models.Account;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends DataTablesRepository<Account, Integer> {

  Account findByUsername(String username);

  Account findByRole(String role);

}
