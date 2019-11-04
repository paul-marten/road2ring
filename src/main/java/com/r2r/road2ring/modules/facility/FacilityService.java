package com.r2r.road2ring.modules.facility;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.util.List;
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
    if(facility.getId() != 0 && facility.getId() != null){
      saved = this.getFacilityById(facility.getId());
    }else{
      saved.setStatus(PublishedStatus.UNPUBLISHED);
    }
    saved.setImage(facility.getImage());
    saved.setName(facility.getName());
    return facilityRepository.save(saved);
  }

  public List<Facility> getAllFacility() {
    return facilityRepository.findAll();
  }

  public List<Facility> getAllFacilityPublished(){
    return facilityRepository.findAllByStatus(PublishedStatus.PUBLISHED);
  }

  public Facility getFacilityById(int id){
    return facilityRepository.findOne(id);
  }

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    Facility save = facilityRepository.findOne(id);
    save.setStatus(statusId);
    facilityRepository.save(save);
  }
}
