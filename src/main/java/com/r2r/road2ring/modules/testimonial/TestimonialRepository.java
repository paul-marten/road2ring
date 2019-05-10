package com.r2r.road2ring.modules.testimonial;

import java.util.List;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends DataTablesRepository<Testimonial, Integer> {
  List<Testimonial> findAll();
}