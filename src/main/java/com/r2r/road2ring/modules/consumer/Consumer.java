package com.r2r.road2ring.modules.consumer;
import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.role.Role;
import com.r2r.road2ring.modules.trip.Trip;
import java.util.List;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consumer")
@Data
@Setter
@Getter
public class Consumer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "consumer_id")
  private Integer id;

  @Column(name = "consumer_username")
  private String username;

  @Column(name = "consumer_email")
  private String email;

  @Column(name = "consumer_password")
  private String password;

  @Column(name = "consumer_picture")
  private String picture;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "consumer_role_id", nullable = false)
  private Role role;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "roadCaptain")
  private List<Trip> trips;
}