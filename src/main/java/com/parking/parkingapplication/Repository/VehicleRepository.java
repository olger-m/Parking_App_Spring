package com.parking.parkingapplication.Repository;

import com.parking.parkingapplication.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
