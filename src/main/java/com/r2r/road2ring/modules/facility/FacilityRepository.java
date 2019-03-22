package com.r2r.road2ring.modules.facility;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends DataTablesRepository<Facility,Integer> {

}
