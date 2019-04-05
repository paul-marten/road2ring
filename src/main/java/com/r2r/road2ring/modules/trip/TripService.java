package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.TripFacility.TripFacilityService;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import com.r2r.road2ring.modules.itinerary.ItineraryService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class TripService {

  TripRepository tripRepository;

  ItineraryService itineraryService;

  TripFacilityService tripFacilityService;

  TripPriceRepository tripPriceRepository;

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

  @Autowired
  public void setTripPriceRepository(
      TripPriceRepository tripPriceRepository) {
    this.tripPriceRepository = tripPriceRepository;
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

  public List<TripPrice> getTripPriceList(int tripId){
    Trip trips = tripRepository.findOne(tripId);
    List<TripPrice> result = trips.getTripPrices();
    return result;
  }

  private List<Integer> getTripFacilityInTrip(List<TripFacility> tripFacilities){
    List<Integer> result = new ArrayList<>();
    for(TripFacility tripFacility : tripFacilities){
      result.add(tripFacility.getFacilityId());
    }
    return result;
  }

//  public List<Trip> findTripPageable(Integer page, Integer limit) {
//    Pageable pageable = new PageRequest(page, limit);
//    List<Trip> result = tripRepository.findAllByOrderByIdAsc(pageable);
//    return result;
//  }

  public Page<Trip> findTripPageablePage(Integer page, Integer limit) {
    Pageable pageable = new PageRequest(page, limit);
    Page<Trip> result = tripRepository.findAllByOrderByIdDesc(pageable);
    return result;
  }

  public TripPrice saveTripPrice(int tripId, TripPrice tripPrice){
    TripPrice saved = new TripPrice();

    if(tripPrice.getId() != null && tripPrice.getId() != 0){
      saved = tripPriceRepository.findOne(tripPrice.getId());
    }

    saved.setDiscount(tripPrice.getDiscount());
    saved.setFinishTrip(tripPrice.getFinishTrip());
    saved.setStartTrip(tripPrice.getStartTrip());
    saved.setPersonPaid(tripPrice.getPersonPaid());
    saved.setStatus(tripPrice.getStatus());
    saved.setPrice(tripPrice.getPrice());
    saved.setTrip(tripRepository.findOne(tripId));

    return tripPriceRepository.save(saved);
  }

  public TripPrice getTripPriceById(int tripPricId){
    return tripPriceRepository.findOne(tripPricId);
  }

  public List<TripItineraryDataView> getTripItineraryGroup(int tripId){
    List<Object[]> a = tripRepository.groupByItineraryGroup(tripId);
    System.out.println(a.size());
    TripItineraryDataView tripItineraryDataView;
    List<TripItineraryDataView> list = new ArrayList<>();
    int index = 1;
    for(Object[] s : a){
      tripItineraryDataView = new TripItineraryDataView();
      tripItineraryDataView.setId(index);
      tripItineraryDataView.setCountEvent(((BigInteger)s[0]).intValue());
      tripItineraryDataView.setGroupEvent((int)s[1]);
      tripItineraryDataView.setGroupTitleEvent((String)s[2]);
      list.add(tripItineraryDataView);
      index++;
    }
    return list;
  }
}
