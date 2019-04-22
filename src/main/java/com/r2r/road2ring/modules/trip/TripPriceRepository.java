package com.r2r.road2ring.modules.trip;

import java.util.Date;
import java.util.List;
import javax.xml.crypto.Data;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPriceRepository extends DataTablesRepository<TripPrice, Integer>{
  List<TripPrice> findAll();
  List<TripPrice> findAllByTripIdOrderByStartTripAsc(Integer id);

  List<TripPrice> findAllByTripIdAndStartTripGreaterThanOrderByStartTripAsc(Integer id, Date startDate);

  TripPrice findOneByTripIdAndStartTrip(Integer tripId, Date startDate);

  /*Find cheapest trip price*/
  TripPrice findTop1ByTripIdAndStartTripGreaterThanOrderByPriceAsc(Integer id, Date startDate);
}
