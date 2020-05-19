package com.example.activitidemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class User implements Serializable {
  private static final long serialVersionUID = 8285221299464780129L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;

  private String rank;

  @ManyToOne
  private User leader;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date inTime;

}
