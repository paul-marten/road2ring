package com.r2r.road2ring.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paulms on 6/14/2017.
 */

@Entity
@Table(name = "account")
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;
  private String username;
  private String password;
  private String role;
  private Set<FutsalField> futsalFields = new HashSet<FutsalField>(0);
  private String plainPassword;
  private String repeatPassword;
  private String name;
  private String phone;
  private int totalField;
  private Date latestUpdate;

  public Account() {
    super();
  }

  public Account(int id, String username, String password, String role,
      Set<FutsalField> futsalFields,
      String plainPassword, String repeatPassword, String name, String phone, int totalField,
      Date latestUpdate) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.futsalFields = futsalFields;
    this.plainPassword = plainPassword;
    this.repeatPassword = repeatPassword;
    this.name = name;
    this.phone = phone;
    this.totalField = totalField;
    this.latestUpdate = latestUpdate;
  }


  @JsonView(ViewJSON.Account.class)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int idSurveyer) {
    this.id = idSurveyer;
  }

  @JsonView(ViewJSON.Account.class)
  @NotEmpty
  @Column(name = "username", unique = true)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonView(ViewJSON.DetailAccount.class)
  @NotEmpty
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  //	@JsonView(DataTablesOutput.View.class)
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
  public Set<FutsalField> getFutsalFields() {
    return futsalFields;
  }

  public void setFutsalFields(Set<FutsalField> futsalFields) {
    this.futsalFields = futsalFields;
  }

  @JsonView(ViewJSON.DetailAccount.class)
  @Column(name = "role")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Transient
  public String getPlainPassword() {
    return plainPassword;
  }

  public void setPlainPassword(String plainPassword) {
    this.password = new BCryptPasswordEncoder().encode(plainPassword);
    this.plainPassword = plainPassword;
  }

  @Transient
  public String getRepeatPassword() {
    return repeatPassword;
  }

  public void setRepeatPassword(String repeatPassword) {
    this.repeatPassword = repeatPassword;
  }

  @JsonView(ViewJSON.Account.class)
  @NotEmpty
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonView(ViewJSON.Account.class)
  @Column(name = "phone", unique = true)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @JsonView(ViewJSON.Account.class)
  @Column(name = "total_field")
  public int getTotalField() {
    return totalField;
  }

  public void setTotalField(int totalField) {
    this.totalField = totalField;
  }

  @JsonView(ViewJSON.DetailAccount.class)
  @Column(name = "latest_update")
  public Date getLatestUpdate() {
    return latestUpdate;
  }

  public void setLatestUpdate(Date latestUpdate) {
    this.latestUpdate = latestUpdate;
  }
}