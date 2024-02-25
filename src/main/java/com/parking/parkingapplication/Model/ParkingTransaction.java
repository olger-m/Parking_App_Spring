package com.parking.parkingapplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking_transactions")
public class ParkingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private Duration duration;
    private Double price=1.0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
