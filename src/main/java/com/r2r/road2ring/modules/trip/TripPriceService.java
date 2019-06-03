package com.r2r.road2ring.modules.trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripPriceService {

  TripPriceRepository tripPriceRepository;

  @Autowired
  public void setTripPriceRepository(TripPriceRepository tripPriceRepository){
    this.tripPriceRepository = tripPriceRepository;
  }

  public TripPriceView bindTripPriceView(TripPrice tripPrice){
    TripPriceView tripPriceView = new TripPriceView();
    tripPriceView.setId(tripPrice.getId());
    tripPriceView.setPersonPaid(tripPrice.getPersonPaid());
    tripPriceView.setStartTrip(tripPrice.getStartTrip());
    tripPriceView.setFinishTrip(tripPrice.getFinishTrip());
    tripPriceView.setDiscount(tripPrice.getDiscount());
    tripPriceView.setPrice(tripPrice.getPrice());
    return tripPriceView;
  }

  public List<TripPriceView> bindListTripPriceView(Integer tripId){
    List<TripPriceView> tripPriceViews = new ArrayList<>();
    List<TripPrice> tripPrices = tripPriceRepository.
        findAllByTripIdAndStatusAndStartTripGreaterThanOrderByStartTripAsc(
            tripId, TripPriceStatus.WAITING, new Date());
    for(TripPrice tripPrice : tripPrices){
      tripPriceViews.add(this.bindTripPriceView(tripPrice));
    }
    return tripPriceViews;
  }

  public TripPrice addPersonTripPrice(Integer tripId, Date startDate){
    TripPrice saved = tripPriceRepository.findOneByTripIdAndStartTrip(tripId,startDate);
    saved.setPersonPaid(saved.getPersonPaid()+1);
    tripPriceRepository.save(saved);
    return saved;
  }

  public void minPersonTripPrice(Integer tripId, Date startDate){
    TripPrice saved = tripPriceRepository.findOneByTripIdAndStartTrip(tripId,startDate);
    saved.setPersonPaid(saved.getPersonPaid()-1);
    tripPriceRepository.save(saved);
  }

  public TripPrice getTripPrice(Integer tripId, Date startDate){
    return tripPriceRepository.findOneByTripIdAndStartTrip(tripId,startDate);
  }
}
