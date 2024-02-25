package com.parking.parkingapplication.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    private CarModel carModel;
    private VehicleColor vehicleColor;
    private Integer yearOfManufacture;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;



    @OneToMany(mappedBy = "vehicle")
    @JsonBackReference  // Add this annotation to break the loop
    private List<ParkingTransaction> parkingTransactions;



}
