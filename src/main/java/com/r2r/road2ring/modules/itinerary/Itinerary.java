package com.r2r.road2ring.modules.itinerary;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.Language;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.trip.Trip;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "itinerary")
@Data
public class Itinerary implements Serializable {

  private static final long serialVersionUID = 8572301472549756664L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "itinerary_id")
  @JsonView(ResponseView.LimitedItinerary.class)
  private Integer id;

  @Column(name = "itinerary_title")
  @JsonView(ResponseView.DefaultItinerary.class)
  private String title;

  @Column(name = "itinerary_image_url")
  @JsonView(ResponseView.DefaultItinerary.class)
  private String imageUrl;

  @Column(name = "itinerary_description")
  @JsonView(ResponseView.DefaultItinerary.class)
  private String description;

  @Column(name = "itinerary_group")
  @JsonView(ResponseView.DetailedItinerary.class)
  private Integer group;

  @Column(name = "itinerary_group_title")
  @JsonView(ResponseView.DefaultItinerary.class)
  private String groupTitle;

  @Transient
  private List<String> groupTitles;

  @Column(name = "itinerary_language")
  @JsonView(ResponseView.DefaultTrip.class)
  private Language language;

  @Column(name = "itinerary_related_itinerary_id")
  @JsonView(ResponseView.DefaultTrip.class)
  private Integer relatedItinerary;

  @ManyToOne(fetch = FetchType.LAZY , optional = true)
  @JoinColumn(name = "itinerary_trip_id" , nullable = true)
  @JsonView(ResponseView.DetailedItinerary.class)
  private Trip trip;
}
