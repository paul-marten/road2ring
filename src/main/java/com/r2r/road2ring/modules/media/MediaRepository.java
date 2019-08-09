package com.r2r.road2ring.modules.media;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {

  List<Media> findAllByAlbumIdOrderByIdAsc(int albumId);

}
