package com.parking.parkingapplication.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Address can't be null")
    private String address;
    private Long stories=1L;
    private Long capacity=1L;
    private Long availableSpaces;


    @OneToMany(mappedBy = "parkingLot")
    @JsonBackReference
    private List<ParkingSpace> parkingSpaces;





}
