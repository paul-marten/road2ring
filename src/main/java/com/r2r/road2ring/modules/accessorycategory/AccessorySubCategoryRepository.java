package com.r2r.road2ring.modules.accessorycategory;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessorySubCategoryRepository extends DataTablesRepository<AccessorySubCategory,Integer> {

  List<AccessorySubCategory> findAllByAccessoryCategoryIdAndStatus(Integer accessoryCategoryId,
      PublishedStatus publishedStatus);

  AccessorySubCategory findAllByTitleIgnoreCaseNotLikeAndStatus(String name,PublishedStatus status);

}
