package com.r2r.road2ring.modules.motor;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends DataTablesRepository<Motor,Integer> {
  List<Motor> findAll();
}
