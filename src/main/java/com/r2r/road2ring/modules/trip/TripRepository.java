package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.itinerary.Itinerary;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends DataTablesRepository<Trip,Integer> {
  List<Trip> findAll();
  Trip findByCreated(Date date);
  @Query(value = "select count(itinerary_id), itinerary_group, itinerary_group_title "
      + "from itinerary where itinerary_trip_id = :tripId "
      + "group by itinerary_group, itinerary_group_title order by itinerary_group asc",
      nativeQuery = true)
  List<Itinerary> groupByItineraryGroup(@Param("tripId") int tripId);
}
