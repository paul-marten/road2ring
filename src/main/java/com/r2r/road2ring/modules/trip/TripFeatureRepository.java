package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripFeatureRepository extends DataTablesRepository<TripFeature,Integer> {

  List<TripFeature> findAllByPublishStatusOrderByUpdatedDesc(PublishedStatus status);

  List<TripFeature> findAll();
}
