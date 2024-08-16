package com.sdms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Data
@Getter
@Setter
@ToString
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studId;
    @Column
    private Long contact;
    @Column
    private Long pincode;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String email;
    @Column
    private String status;

}