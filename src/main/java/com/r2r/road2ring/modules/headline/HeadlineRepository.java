package com.r2r.road2ring.modules.headline;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadlineRepository extends DataTablesRepository<Headline, Integer> {
  List<Headline> findAll();
}
