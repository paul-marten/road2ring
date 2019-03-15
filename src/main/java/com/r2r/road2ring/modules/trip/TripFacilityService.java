package com.r2r.road2ring.modules.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripFacilityService {

  TripFacilityRepository tripFacilityRepository;

  @Autowired
  public void setTripFacilityRepository(TripFacilityRepository tripFacilityRepository){
    this.tripFacilityRepository = tripFacilityRepository;
  }
}
