package com.r2r.road2ring.modules.itinerary;

import com.r2r.road2ring.modules.trip.Trip;
import com.r2r.road2ring.modules.trip.TripService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

  ItineraryRepository itineraryRepository;

  TripService tripService;

  @Autowired
  public void setItineraryRepository(ItineraryRepository itineraryRepository) {
    this.itineraryRepository = itineraryRepository;
  }

  @Autowired
  public void setTripService(TripService tripService) {
    this.tripService = tripService;
  }

  public DataTablesOutput<Itinerary> getDatatableItinerary(DataTablesInput input) {
    DataTablesOutput<Itinerary> itineraries = itineraryRepository.findAll(input);

    List<Itinerary> builtItinerary = itineraries.getData();
    itineraries.setData(builtItinerary);

    return itineraries;
  }

  public Itinerary saveItinerary(Itinerary itinerary, Trip trip) {
    Itinerary saved = new Itinerary();
    if(itinerary.getId() != 0){
      saved = itineraryRepository.findOne(itinerary.getId());
    }
    saved.setDescription(itinerary.getDescription());
    saved.setTitle(itinerary.getTitle());
    saved.setImageUrl(itinerary.getImageUrl());
    saved.setGroup(itinerary.getGroup());
    saved.setGroupTitle(itinerary.getGroupTitle());
    saved.setTrip(trip);
    return itineraryRepository.save(saved);
  }

  public void removeTab(Integer id) {
    itineraryRepository.delete(id);
  }

  public List<Itinerary> saveListOfItinerary(List<Itinerary> itineraries, Trip trip) {
    List<Itinerary> result = new ArrayList<>();
    List<Itinerary> deletedItineraries = trip.getDeletedItinerary();

    if (deletedItineraries != null && deletedItineraries.size() != 0) {
      for (Itinerary deletedItinerary : deletedItineraries) {
          removeTab(deletedItinerary.getId());
      }
    }
    for (Itinerary saved : itineraries) {
      result.add(saveItinerary(saved, trip));
    }
    return result;
  }

  public List<Itinerary> getItineraryByGroupAndTrip(int group, int tripId){
    Trip trip = tripService.getTripById(tripId);
    return itineraryRepository.findAllByGroupAndTripOrderByIdAsc(group, trip);
  }

  public List<Integer> getItineraryGroupInTrip(Integer tripId){
    List<Integer> result = itineraryRepository.findAllItineraryGroup(tripId);
    return result;
  }

  public List<Itinerary> getItineraryTripByGroup(Integer groupId, Integer tripId){
    List<Itinerary> result = itineraryRepository.findAllByGroupAndTripIdOrderByIdAsc(groupId,tripId);
    return result;
  }
}
