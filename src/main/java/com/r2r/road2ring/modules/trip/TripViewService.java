package com.r2r.road2ring.modules.trip;

import com.github.javafaker.Faker;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import com.r2r.road2ring.modules.itinerary.ItineraryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TripViewService {

  TripService tripService;

  ItineraryService itineraryService;

  @Autowired
  public void setTripService(TripService tripService){
    this.tripService = tripService;
  }

  @Autowired
  public void setItineraryService(ItineraryService itineraryService){
    this.itineraryService = itineraryService;
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
    tripView.setIconPublisher(faker.internet().image());
    tripView.setTripPrice(faker.number().randomDigit());
    return tripView;
  }

  public TripViewDetail getDetailTripView(Integer tripId){
    Faker faker = new Faker();

    Trip trip = tripService.getTripById(tripId);

    TripViewDetail tripViewDetail = new TripViewDetail();
    tripViewDetail.setId(trip.getId());
    tripViewDetail.setTitle(trip.getTitle());
    tripViewDetail.setDuration(trip.getDuration());
    tripViewDetail.setCoverLandscape(faker.internet().image());
    tripViewDetail.setIconPublisher(faker.internet().image());
    tripViewDetail.setTripPrice(faker.number().randomDigit());


    tripViewDetail.setCoverPotrait(faker.internet().image());
    tripViewDetail.setDescription(trip.getDescription());
    tripViewDetail.setDistance(trip.getDistance());
    tripViewDetail.setIconCover(faker.internet().image());
    tripViewDetail.setMaxRider(trip.getMaxRider());
    tripViewDetail.setTerrain(trip.getTerrain());
    tripViewDetail.setRoadCaptainDescription(trip.getRoadCaptain().getDescription());
    tripViewDetail.setImageRoadCaptain(faker.internet().image());
    tripViewDetail.setFacilityNotIncluded(trip.getFacilityNot());

    tripViewDetail.setItineraries(this.getListItineraryTrip(trip.getId()));

    return tripViewDetail;
  }

  private TripViewItinerary getItineraryTrip(Integer tripId, Integer groupId){
    TripViewItinerary tripViewItinerary = new TripViewItinerary();
    List<Itinerary> itineraries = itineraryService.getItineraryTripByGroup(groupId,tripId);

    List<ItineraryDetail> itineraryDetails = new ArrayList<>();

    Faker faker = new Faker();

    for(Itinerary itinerary : itineraries){
      ItineraryDetail itineraryDetail = new ItineraryDetail();
      itineraryDetail.setTitle(itinerary.getTitle());
      itineraryDetail.setDescription(itinerary.getDescription());
      itineraryDetail.setImageItinerary(faker.internet().image());
      itineraryDetails.add(itineraryDetail);
      tripViewItinerary.setGroupTitle(itinerary.getGroupTitle());
    }

    tripViewItinerary.setDetails(itineraryDetails);

    return tripViewItinerary;
  }

  private List<TripViewItinerary> getListItineraryTrip(Integer tripId){
    List<TripViewItinerary> tripViewItineraries = new ArrayList<>();
    List<Integer> groupIdTrip = itineraryService.getItineraryGroupInTrip(tripId);
    for(Integer groupdId : groupIdTrip){
      tripViewItineraries.add(this.getItineraryTrip(tripId,groupdId));
    }
    return tripViewItineraries;
  }
}
