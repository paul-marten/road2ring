package com.r2r.road2ring.modules.TripFacility;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripFacilityRepository extends JpaRepository<TripFacility, Integer> {
  List<TripFacility> findByTripIdOrderByIdAsc(Integer trip);
}
