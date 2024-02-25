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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking_space")
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private ParkingSpaceStatus parkingSpaceStatus=ParkingSpaceStatus.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "parking_space_vehicle")
    private Vehicle vehicle;
    private Boolean occupied=Boolean.FALSE;
    @NotNull(message = "price can't be null")
    private Double costPerHour=1.0;

    @OneToMany(mappedBy = "id")
    @JsonBackReference  // Add this annotation to break the loop
    private List<ParkingTransaction> parkingTransactionListOfSpace;


    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;



}
