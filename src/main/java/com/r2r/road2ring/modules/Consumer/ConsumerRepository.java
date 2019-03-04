package com.r2r.road2ring.modules.Consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Integer> {

}
