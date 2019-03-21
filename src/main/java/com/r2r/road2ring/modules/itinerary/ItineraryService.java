package com.r2r.road2ring.modules.itinerary;

import com.r2r.road2ring.modules.trip.Trip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

  ItineraryRepository itineraryRepository;

  @Autowired
  public void setItineraryRepository(ItineraryRepository itineraryRepository) {
    this.itineraryRepository = itineraryRepository;
  }


  public DataTablesOutput<Itinerary> getDatatableItinerary(DataTablesInput input,
      int tripId) {
    DataTablesOutput<Itinerary> itineraries = itineraryRepository.findAll(input);

    List<Itinerary> builtItinerary = itineraries.getData();
    itineraries.setData(builtItinerary);

    return itineraries;
  }

  public Itinerary saveItinerary(Itinerary itinerary, Trip trip, List<String> groupTitle) {
    Itinerary saved = new Itinerary();
    saved.setDescription(itinerary.getDescription());
    saved.setTitle(itinerary.getTitle());
    saved.setImageUrl(itinerary.getImageUrl());
    saved.setGroup(itinerary.getGroup());
    saved.setGroupTitle(groupTitle.get(itinerary.getGroup()));
    saved.setTrip(trip);
    return itineraryRepository.save(saved);
  }

  public List<Itinerary> saveListOfItinerary(List<Itinerary> itineraries, Trip trip,
      List<String> groupTitles) {
    List<Itinerary> result = new ArrayList<>();
    for (Itinerary saved : itineraries) {
      result.add(saveItinerary(saved, trip, groupTitles));
    }
    return result;
  }

}
