package com.r2r.road2ring.modules.testimonial;

import com.r2r.road2ring.modules.common.PublishedStatus;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends DataTablesRepository<Testimonial, Integer> {
  List<Testimonial> findAll();
  List<Testimonial> findAllByOrderByIdDesc(Pageable pageable);
  List<Testimonial> findAllByPublishedStatusOrderByIdDesc(Pageable pageable, PublishedStatus publishedStatus);
}
