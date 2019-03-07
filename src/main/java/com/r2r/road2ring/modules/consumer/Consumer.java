package com.r2r.road2ring.modules.consumer;
import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.role.Role;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "consumer_role_id", nullable = false)
  private Role role;
}