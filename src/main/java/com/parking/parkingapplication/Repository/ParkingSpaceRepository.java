package com.parking.parkingapplication.Repository;

import com.parking.parkingapplication.Model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace,Long> {
List<ParkingSpace> findByOccupied(boolean occupied);
}
