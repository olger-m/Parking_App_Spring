package com.parking.parkingapplication.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "car_owner")
public class Owner extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "id")
    private List<Vehicle> vehicleList;
    @OneToMany(mappedBy = "id")
    private List<ParkingTransaction> parkingTransactions;

    public Owner(Long id, String name, String address, String email, String phoneNumber) {
        super(name, address, email, phoneNumber);
        this.id = id;
    }

    public Owner() {
        super();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public void setAddress(String address) {
        super.setAddress(address);
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

}
