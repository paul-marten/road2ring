package com.r2r.road2ring.modules.role;

import com.r2r.road2ring.modules.consumer.Consumer;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "role_id")
  private int id;

  @Column(name = "role_name")
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
  private Set<Consumer> consumers;
}