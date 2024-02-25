package com.parking.parkingapplication.Repository;

import com.parking.parkingapplication.Model.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction,Long> {
}
