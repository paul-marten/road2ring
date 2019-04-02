package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.TripFacility.TripFacilityService;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import com.r2r.road2ring.modules.itinerary.ItineraryService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class TripService {

  TripRepository tripRepository;

  ItineraryService itineraryService;

  TripFacilityService tripFacilityService;

  @Autowired
  public void setTripRepository(TripRepository tripRepository){
    this.tripRepository = tripRepository;
  }

  @Autowired
  public void setItineraryService(ItineraryService itineraryService){
    this.itineraryService = itineraryService;
  }

  @Autowired
  public void setTripFacilityService(TripFacilityService tripFacilityService){
    this.tripFacilityService = tripFacilityService;
  }

  public Trip saveTrip(Trip trip){
    Trip saved = new Trip();
    Boolean checkNewTrip = false;
    if(trip.getId() != null && trip.getId() != 0) {
      checkNewTrip = true;
      saved = tripRepository.findOne(trip.getId());
    } else {
      saved.setCreated(new Date());
    }
    saved.setDescription(trip.getDescription());
    saved.setDistance(trip.getDistance());
    saved.setMaxRider(trip.getMaxRider());
    saved.setIconCover(trip.getIconCover());
    saved.setIconPublisher(trip.getIconPublisher());
    saved.setTerrain(trip.getTerrain());
    saved.setDuration(trip.getDuration());
    if(trip.getRoadCaptain() != null && trip.getRoadCaptain().getId() != 0) {
      saved.setRoadCaptain(trip.getRoadCaptain());
    }
    saved.setMeetingPoint(trip.getMeetingPoint());
    saved.setUpdated(new Date());
    saved.setTag(trip.getTag());
    saved.setFacilityNot(trip.getFacilityNot());
    saved.setTitle(trip.getTitle());
    saved.setLocation(trip.getLocation());
    saved = tripRepository.save(saved);

    if( saved != null){
      Trip tripSaved;
      if(checkNewTrip != false){
        tripSaved = tripRepository.findOne(trip.getId());
      }else {
        tripSaved = tripRepository.findByCreated(saved.getCreated());
      }
      tripFacilityService.saveListOfTripFacilityByInteger(trip.getFacilityInclude(),tripSaved);

    }
    return saved;
  }

  public List<Trip> getAllTrip(){
    return tripRepository.findAll();
  }

  public DataTablesOutput<Trip> getDatatableContents(DataTablesInput input) {
    DataTablesOutput<Trip> trip = tripRepository.findAll(input);
    return trip;
  }

  public Trip getTripById(int id){
    Trip trip = tripRepository.findOne(id);
    trip.setFacilityInclude(this.getTripFacilityInTrip(trip.getTripFacilities()));
    return trip;
  }

  public List<Itinerary> getTripItinerary(int tripId){
    Trip trips = tripRepository.findOne(tripId);
    List<Itinerary> result = trips.getItineraries();
    return result;
  }

  private List<Integer> getTripFacilityInTrip(List<TripFacility> tripFacilities){
    List<Integer> result = new ArrayList<>();
    for(TripFacility tripFacility : tripFacilities){
      result.add(tripFacility.getFacilityId());
    }
    return result;
  }
}
