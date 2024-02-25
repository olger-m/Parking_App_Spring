package com.parking.parkingapplication.Repository;

import com.parking.parkingapplication.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
