package com.r2r.road2ring.modules.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

  TripRepository tripRepository;

  @Autowired
  public void setTripRepository(TripRepository tripRepository){
    this.tripRepository = tripRepository;
  }

  public Trip saveTrip(Trip trip){
    Trip saved = new Trip();
    saved.setDescription(trip.getDescription());
    saved.setDetailDescription(trip.getDetailDescription());
    saved.setDuration(trip.getDuration());
    return tripRepository.save(saved);
  }
}
