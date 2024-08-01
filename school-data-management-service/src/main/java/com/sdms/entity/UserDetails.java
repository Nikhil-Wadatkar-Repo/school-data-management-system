package com.sdms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
public class UserDetails {
    @Id
    private Long userId;
    @Column
    private Long contact;
    @Column
    private Long pincode;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String userType;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String status;
    @Column
    private Integer age;
}
