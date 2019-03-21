package com.r2r.road2ring.modules.trip;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends DataTablesRepository<Trip,Integer> {
  List<Trip> findAll();

}
