package com.r2r.road2ring.modules.gallery;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends DataTablesRepository<Gallery, Integer> {
  List<Gallery> findAll();
  List<Gallery> findAllByOrderByIdDesc(Pageable pageable);
  List<Gallery> findAllByPublishedStatusOrderByIdDesc(Pageable pageable, PublishedStatus publishedStatus);
}
