package com.parking.parkingapplication.Service;

import com.parking.parkingapplication.Model.ParkingLot;
import com.parking.parkingapplication.Model.ParkingSpace;
import com.parking.parkingapplication.Repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {
private final ParkingLotRepository parkingLotRepository;
private final ParkingSpaceService parkingSpaceService;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingSpaceService parkingSpaceService) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingSpaceService = parkingSpaceService;
    }


    public List<ParkingLot> getAllParkingLots(){
        return parkingLotRepository.findAll();
    }

    public ParkingLot getParkingLotById(Long id){
        return parkingLotRepository.findById(id).orElse(null);
    }
    public void updateParkingLot(ParkingLot parkingLot){
        parkingLotRepository.save(parkingLot);
    }

    public void deleteParkingLotById(Long id){
        parkingLotRepository.deleteById(id);
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        ParkingLot Parking = parkingLotRepository.save(parkingLot);
        parkingSpaceService.createParkingSpace(Parking);
        return Parking;
    }


    public List<ParkingSpace> getAllParkingSpaces(Long id) {
        return parkingLotRepository.findById(id).orElseThrow().getParkingSpaces();
    }
    public List<ParkingSpace> getAvailableParkingSpaces(Long id){
        return parkingLotRepository.findById(id).orElse(null)
                .getParkingSpaces().stream().filter(e->e.getOccupied().equals(false)).collect(Collectors.toList());
    }
}
