package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.Road2RingException;
import com.r2r.road2ring.modules.motor.Motor;
import com.r2r.road2ring.modules.motor.MotorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class TripPriceMotorService {

  @Autowired
  TripPriceMotorRepository tripPriceMotorRepository;

  @Autowired
  MotorService motorService;


  public List<TripPriceMotor> getDatatable(){
    return tripPriceMotorRepository.findAll();
  }

  public List<Motor> getTripPriceMotor(Integer tripPriceMotorId){
    List<Motor> result = new ArrayList<Motor>();
//    List<TripPriceMotor> listTripPriceMotor = tripPriceMotorRepository
//        .findAllByTripPriceIdAndStockGreaterThan(tripPriceMotorId,0);
//    for (TripPriceMotor item :
//        listTripPriceMotor) {
//      result.add(item.getBike());
//    }

    result = motorService.getAllMotor();

    return result;
  }


  public List<TripPriceMotor> saveList(List<TripPriceMotor> listTripPriceMotor){
    List<TripPriceMotor> result = new ArrayList<TripPriceMotor>();
    for (TripPriceMotor item :
        listTripPriceMotor) {
      result.add(save(item));
    }
    return result;

  }

  public TripPriceMotor reverseStock(Integer idTripPriceMotor){
    TripPriceMotor result = tripPriceMotorRepository.findOne(idTripPriceMotor);
    result.setStock(result.getStock() + 1);
    return save(result);
  }

  public TripPriceMotor substractStock(Integer idTripPriceMotor ) throws Road2RingException {
    TripPriceMotor result = tripPriceMotorRepository.findOne(idTripPriceMotor);
    if(result.getStock() - 1 >= 0)
      return save(result);
    else
      throw new Road2RingException("Stock not Available",500);
  }

  public TripPriceMotor save(TripPriceMotor saved){

    return tripPriceMotorRepository.save(saved);
  }

}
