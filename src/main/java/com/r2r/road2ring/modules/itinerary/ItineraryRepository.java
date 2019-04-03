package com.r2r.road2ring.modules.itinerary;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends DataTablesRepository<Itinerary, Integer> {
//  List<Itinerary> findAllByTrip
}
