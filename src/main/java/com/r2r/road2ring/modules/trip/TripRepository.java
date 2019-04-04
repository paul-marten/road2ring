package com.r2r.road2ring.modules.trip;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends DataTablesRepository<Trip,Integer> {
  List<Trip> findAll();
  Trip findByCreated(Date date);

  /*Trip Pageable*/
//  List<Trip>findAllByOrderByIdAsc(Pageable pageable);

//  Page<Trip> findAllByOrderByIdAsc(Pageable pageable);

  Page<Trip> findAllByOrderByIdDesc(Pageable pageable);
}
