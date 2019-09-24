package com.r2r.road2ring.modules.trip;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPriceMotorRepository extends DataTablesRepository<TripPriceMotor,Integer>{

  List<TripPriceMotor> findAll();
  List<TripPriceMotor> findAllByTripPriceIdAndStockGreaterThan(Integer tripPriceId,Integer stock);

}
