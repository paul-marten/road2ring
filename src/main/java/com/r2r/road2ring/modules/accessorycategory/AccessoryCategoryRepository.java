package com.r2r.road2ring.modules.accessorycategory;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryCategoryRepository extends DataTablesRepository<AccessoryCategory, Integer> {
  List<AccessoryCategory> findAll();
  List<AccessoryCategory> findAllByOrderByIdAsc();
}
