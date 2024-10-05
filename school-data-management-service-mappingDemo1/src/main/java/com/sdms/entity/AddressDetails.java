package com.sdms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Embeddable
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressID;
    @Column
    private String city;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_address_fk")
//    private PersonDetails personDetails;
//
//    public PersonDetails getPersonDetails() {
//        return personDetails;
//    }
//
//    public void setPersonDetails(PersonDetails personDetails) {
//        this.personDetails = personDetails;
//    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
