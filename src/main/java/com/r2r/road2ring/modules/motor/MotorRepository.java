package com.r2r.road2ring.modules.motor;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends DataTablesRepository<Motor,Integer> {
  List<Motor> findAll();
  List<Motor> findAllByOrderByIdDesc(Pageable pageable);
  Motor findOneByTitle(String title);
  List<Motor> findAllByStatus(PublishedStatus publishedStatus);
}
