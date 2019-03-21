package com.r2r.road2ring.modules.motor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorService {

  MotorRepository motorRepository;

  @Autowired
  public void setMotorRepository(MotorRepository motorRepository){
    this.motorRepository = motorRepository;
  }

  public Motor saveMotor(Motor motor){
    Motor saved = new Motor();
    saved.setBrand(motor.getBrand());
    saved.setCapacity(motor.getCapacity());
    saved.setDiscount(motor.getDiscount());
    saved.setDescription(motor.getDescription());
    saved.setPrice(motor.getPrice());
    saved.setTitle(motor.getTitle());
    saved.setBrand(motor.getBrand());
    saved.setPicture(motor.getPicture());
    return motorRepository.save(saved);
  }

}
