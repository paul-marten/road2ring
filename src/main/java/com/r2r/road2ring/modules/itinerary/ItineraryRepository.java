package com.r2r.road2ring.modules.itinerary;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends DataTablesRepository<Itinerary, Integer> {
//  List<Itinerary> findAllByTrip


  @Query(value = "SELECT DISTINCT itinerary_group "
      + "FROM itinerary "
      + "WHERE itinerary_trip_id = :tripId",
      nativeQuery = true
  )
  List<Integer> findAllItineraryGroup(@Param("tripId") Integer tripId);

//  @Query(value = "SELECT *"
//      + "FROM itinerary "
//      + "WHERE itinerary_trip_id = :tripId "
//      + "AND itinerary_group = :groupId",
//      nativeQuery = true
//  )
//  List<Itinerary> findByGroupAndTripIdOrderByIdAsc(
//      @Param("groupId") Integer groupId,
//      @Param("tripId") Integer tripId);

  List<Itinerary> findAllByGroupAndTripIdOrderByIdAsc(Integer groupId, Integer tripId);
}
