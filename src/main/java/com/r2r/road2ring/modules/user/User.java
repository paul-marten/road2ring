package com.r2r.road2ring.modules.user;

import com.r2r.road2ring.modules.role.Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_r2r")
@Data
public class User implements Serializable {

  private static final long serialVersionUID = -3018339974747543397L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "user_username")
  private String username;

  @Column(name = "user_password")
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_role_id", nullable = false)
  private Role role;

  @Column(name = "user_email")
  private String email;

  @Column(name = "user_ktp_number")
  private Long ktpNumber;

//  private String facebookPageID;
//  private String twitterPageID;
//  private String facebookPassword;
//  private String twitterPassword;
}