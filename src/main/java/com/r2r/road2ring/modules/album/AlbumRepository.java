package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends DataTablesRepository<Album, Integer> {
  List<Album> findAll();

  List<Album> findTop5ByStatusAndTitleIgnoreCaseContaining(PublishedStatus published, String keyword);

  List<Album> findTop5ByTitleIgnoreCaseContaining(String keyword);
}
