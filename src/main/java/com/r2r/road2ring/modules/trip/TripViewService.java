package com.r2r.road2ring.modules.trip;

import com.github.javafaker.Faker;
import com.r2r.road2ring.modules.common.ResponseMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TripViewService {

  TripService tripService;

  @Autowired
  public void setTripService(TripService tripService){
    this.tripService = tripService;
  }

//  public List<TripView> getListTripView(Integer page, Integer limit){
//    List<TripView> result = new ArrayList<>();
//    Page<Trip> trips = tripService.findTripPageablePage(page,limit);
//    for(Trip trip : trips){
//      result.add(this.getTripView(trip));
//    }
//    return result;
//  }

  public ResponseMessage getListTripView(Integer page, Integer limit){
    ResponseMessage responseMessage = new ResponseMessage();
    List<TripView> result = new ArrayList<>();
    Page<Trip> trips = tripService.findTripPageablePage(page,limit);
    for(Trip trip : trips){
      result.add(this.getTripView(trip));
    }
    responseMessage.setObject(result);
    responseMessage.setTotalPage(trips.getTotalPages());
    return responseMessage;
  }


  public TripView getTripView(Trip trip){
    Faker faker = new Faker();
    TripView tripView = new TripView();
    tripView.setId(trip.getId());
    tripView.setTitle(trip.getTitle());
    tripView.setDuration(trip.getDuration());
    tripView.setCoverLandscape(faker.internet().image());
    return tripView;
  }
}
