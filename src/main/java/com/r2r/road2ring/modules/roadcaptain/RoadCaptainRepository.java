package com.r2r.road2ring.modules.roadcaptain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadCaptainRepository extends JpaRepository<RoadCaptain,Integer> {

}
