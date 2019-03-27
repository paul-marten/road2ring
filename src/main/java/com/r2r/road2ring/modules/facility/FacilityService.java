package com.r2r.road2ring.modules.facility;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {

  FacilityRepository facilityRepository;

  @Autowired
  public void setFacilityRepository(FacilityRepository facilityRepository){
    this.facilityRepository = facilityRepository;
  }

  public Facility saveFacility(Facility facility){
    Facility saved = new Facility();
    saved.setImage(facility.getImage());
    saved.setName(facility.getName());
    return facilityRepository.save(saved);
  }

  public List<Facility> getAllFacility() {
    return facilityRepository.findAll();
  }
}
