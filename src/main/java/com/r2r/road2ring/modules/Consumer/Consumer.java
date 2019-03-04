package com.r2r.road2ring.modules.Consumer;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name = "consumer")
@Data
public class Consumer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "consumer_id")
  private Integer id;

  @Column(name = "consumer_id")
  private String username;
  @Column(name = "consumer_email")
  private String email;
  @Column(name = "consumer_password")
  private String password;
}