package com.r2r.road2ring.modules.user;

import java.util.List;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRoleRepository extends DataTablesRepository<UserRequestRole,Integer> {

  UserRequestRole findOneByUserIdAndStatus(Integer userId,Boolean status);

  List<UserRequestRole> findAll();

  List<UserRequestRole> findAllByStatus(Boolean status);
}
