package com.r2r.road2ring.modules.accessorycategory;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryCategoryRepository extends DataTablesRepository<AccessoryCategory, Integer> {
  List<AccessoryCategory> findAll();
  List<AccessoryCategory> findAllByOrderByIdAsc();
  List<AccessoryCategory> findAllByTitleIgnoreCaseNotLike(String title);
  AccessoryCategory findOneByTitleIgnoreCase(String title);
  List<AccessoryCategory> findAllByStatus(PublishedStatus status);
  List<AccessoryCategory> findAllByTitleIgnoreCaseNotLikeAndStatus(String title, PublishedStatus publishedStatus);
}
