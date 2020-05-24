package com.example.activitidemo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private String birthday;
    private String nation;
    private String politics;
    private String enrollmentTime;
    private String idCard;
    private String phone;
    private String university;
    private String college;
    private String dept;
    private String grade;
    private String residence;
    private String homeNum;
    private String homeIncome;
    private String monthIncome;
    private String sourceIncome;
    private String addr;
    private String postalCode;
    private String achievement;
    private String reason;
    private Integer member;
    private String content;

}
