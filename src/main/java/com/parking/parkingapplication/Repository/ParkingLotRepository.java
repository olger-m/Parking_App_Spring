package com.parking.parkingapplication.Repository;

import com.parking.parkingapplication.Model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {
}
