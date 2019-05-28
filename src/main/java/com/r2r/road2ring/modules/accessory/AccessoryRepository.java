package com.r2r.road2ring.modules.accessory;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends DataTablesRepository<Accessory,Integer> {
  List<Accessory> findAll();
  List<Accessory> findAllByAccessoryCategoryIdOrderByIdAsc(Integer tripId);
}
