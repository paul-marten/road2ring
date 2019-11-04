package com.r2r.road2ring.modules.TripFacility;

import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.TripFacility.TripFacilityRepository;
import com.r2r.road2ring.modules.facility.Facility;
import com.r2r.road2ring.modules.facility.FacilityRepository;
import com.r2r.road2ring.modules.trip.Trip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripFacilityService {

  TripFacilityRepository tripFacilityRepository;

  FacilityRepository facilityRepository;

  @Autowired
  public void setTripFacilityRepository(TripFacilityRepository tripFacilityRepository){
    this.tripFacilityRepository = tripFacilityRepository;
  }

  @Autowired
  public void setFacilityRepository(FacilityRepository facilityRepository){
    this.facilityRepository = facilityRepository;
  }

  public TripFacility saveTripFacility(Facility facility, Trip trip){
    TripFacility saved = new TripFacility();
    saved.setFacilityName(facility.getName());
    saved.setFacilityImage(facility.getImage());
    saved.setFacilityId(facility.getId());
    saved.setTrip(trip);
    return tripFacilityRepository.save(saved);
  }

//  public List<TripFacility> saveListOfTripFacility(List<TripFacility> tripFacilityList, Trip trip){
//    List<TripFacility> result = new ArrayList<>();
//    for(TripFacility saved : tripFacilityList){
//      result.add(saveTripFacility(saved,trip));
//    }
//    return result;
//  }

  public void saveListOfTripFacilityByInteger(List<Integer> tripFacilities, Trip trip){
    this.deleteListTripFacilities(trip);
    for (Integer result : tripFacilities){
      Facility facility = facilityRepository.findOne(result);
      this.saveTripFacility(facility,trip);
    }
  }

  private void deleteListTripFacilities(Trip trip){
    List<TripFacility> delete = this.getTripFacilityOnTrip(trip.getId());
    tripFacilityRepository.delete(delete);
  }


  public List<TripFacility> getTripFacilityOnTrip(Integer tripId){
    return tripFacilityRepository.findByTripIdOrderByIdAsc(tripId);
  }

}
