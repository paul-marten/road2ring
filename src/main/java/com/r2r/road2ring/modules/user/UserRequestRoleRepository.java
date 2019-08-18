package com.r2r.road2ring.modules.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRoleRepository extends JpaRepository<UserRequestRole,Integer> {

  UserRequestRole findOneByUserIdAndStatus(Integer userId,Boolean status);

}
