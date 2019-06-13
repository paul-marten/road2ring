package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.user.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestTripService {

  RequestTripRepository requestTripRepository;

  @Autowired
  public void setRequestTripRepository(RequestTripRepository requestTripRepository){
    this.requestTripRepository = requestTripRepository;
  }

  public RequestTrip saveRequestTrip(User user,RequestTrip requestTrip){
    RequestTrip saved = new RequestTrip();
    saved.setMaxRider(requestTrip.getMaxRider());
    saved.setStartDate(new Date(requestTrip.getStartTimestamp()));
    saved.setTrip(requestTrip.getTrip());
    saved.setUserEmail(user.getEmail());
    requestTripRepository.save(saved);
    return saved;
  }

  public List<RequestTrip> getAllRequest(){
    return requestTripRepository.findAll();
  }

}
