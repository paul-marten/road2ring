package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.itinerary.Itinerary;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends DataTablesRepository<Trip,Integer> {
  List<Trip> findAll();
  Trip findByCreated(Date date);

  @Query(value = "select count(itinerary_id) as countEvent, itinerary_group as groupEvent, itinerary_group_title as groupTitleEvent "
      + "from itinerary where itinerary_trip_id = :tripId "
      + "group by groupEvent, groupTitleEvent order by groupEvent asc",
      nativeQuery = true)
  List<Object[]> groupByItineraryGroup(@Param("tripId") int tripId);

  /*Trip Pageable*/
//  List<Trip>findAllByOrderByIdAsc(Pageable pageable);

//  Page<Trip> findAllByOrderByIdAsc(Pageable pageable);

  Page<Trip> findAllByOrderByIdDesc(Pageable pageable);
}
