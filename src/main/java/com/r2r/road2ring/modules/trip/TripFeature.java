package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.ResponseView;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "trip_feature")
@Data
public class TripFeature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trip_feature_id")
  @JsonView(ResponseView.LimitedTrip.class)
  private Integer id;

  @Column(name = "trip_feature_title")
  @JsonView(ResponseView.LimitedTrip.class)
  private String title;

  @Column(name = "trip_feature_icon_cover")
  @JsonView(ResponseView.DefaultTrip.class)
  private String icon;

  @Column(name = "trip_feature_cover")
  @JsonView(ResponseView.DefaultTrip.class)
  private String cover;

  @Column(name = "trip_feature_link")
  @JsonView(ResponseView.DefaultTrip.class)
  private String link;

  @Column(name = "trip_feature_status")
  @JsonView(ResponseView.DefaultTrip.class)
  private PublishedStatus publishStatus;

  @CreationTimestamp
  @Column(name="user_request_rc_created_at")
  private Date created;

  @UpdateTimestamp
  @Column(name="user_request_rc_updated_at")
  private Date updated;



}
