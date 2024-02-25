package com.parking.parkingapplication.Service;

import com.parking.parkingapplication.Model.ParkingLot;
import com.parking.parkingapplication.Model.ParkingSpace;
import com.parking.parkingapplication.Repository.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;


    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }


    public List<ParkingSpace> getAllParkingSpaces(){
        return parkingSpaceRepository.findAll();
    }

    public ParkingSpace getParkingSpaceById(Long id){
        return parkingSpaceRepository.findById(id).orElse(null);
    }
    public void updateParkingSpace(ParkingSpace parkingSpace){
        parkingSpaceRepository.save(parkingSpace);
    }

    public void deleteParkingSpaceById(Long id){
        parkingSpaceRepository.deleteById(id);
    }


    public List<ParkingSpace> createParkingSpace(ParkingLot parkingLot) {
        List<ParkingSpace>parkingSpaceList=new ArrayList<>();
            for (int i = 0; i < parkingLot.getCapacity(); i++) {
                ParkingSpace parkingSpace=new ParkingSpace();
                parkingSpace.setParkingLot(parkingLot);
                parkingSpaceList.add(parkingSpace);
            }
             return parkingSpaceRepository.saveAll(parkingSpaceList);
    }
    public List<ParkingSpace> getNotOccupied() {
        return parkingSpaceRepository.findByOccupied(false);
    }
}
