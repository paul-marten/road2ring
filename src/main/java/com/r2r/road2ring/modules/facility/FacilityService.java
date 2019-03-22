package com.r2r.road2ring.modules.facility;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {

  FacilityRepository facilityRepository;

  @Autowired
  public void setFacilityRepository(FacilityRepository facilityRepository){
    this.facilityRepository = facilityRepository;
  }

  public DataTablesOutput<Facility> findDatatable(@Valid DataTablesInput input) {
    return facilityRepository.findAll(input);
  }

  public Facility saveFacility(Facility facility){
    Facility saved = new Facility();
    saved.setImage(facility.getImage());
    saved.setName(facility.getName());
    return facilityRepository.save(saved);
  }

}
