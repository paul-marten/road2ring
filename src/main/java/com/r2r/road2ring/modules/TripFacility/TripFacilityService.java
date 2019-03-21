package com.r2r.road2ring.modules.TripFacility;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.TripFacility.TripFacilityRepository;
import com.r2r.road2ring.modules.trip.Trip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripFacilityService {

  TripFacilityRepository tripFacilityRepository;

  @Autowired
  public void setTripFacilityRepository(TripFacilityRepository tripFacilityRepository){
    this.tripFacilityRepository = tripFacilityRepository;
  }

  public TripFacility saveTripFacility(TripFacility tripFacility, Trip trip){
    TripFacility saved = new TripFacility();
    saved.setFacilityName(tripFacility.getFacilityName());
    saved.setTrip(trip);
    saved.setIsIncluded(tripFacility.getIsIncluded());
    return tripFacilityRepository.save(saved);
  }

  public List<TripFacility> saveListOfTripFacility(List<TripFacility> tripFacilityList, Trip trip){
    List<TripFacility> result = new ArrayList<>();
    for(TripFacility saved : tripFacilityList){
      result.add(saveTripFacility(saved,trip));
    }
    return result;
  }
}
