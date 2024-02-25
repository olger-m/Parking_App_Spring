package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.Model.ParkingLot;
import com.parking.parkingapplication.Model.ParkingSpace;
import com.parking.parkingapplication.Service.ParkingLotService;
import com.parking.parkingapplication.Service.ParkingSpaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkingspace")
public class ParkingSpaceController {
    private final ParkingSpaceService parkingSpaceService;
    private final ParkingLotService parkingLotService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService, ParkingLotService parkingLotService) {
        this.parkingSpaceService = parkingSpaceService;
        this.parkingLotService = parkingLotService;
    }
    @GetMapping("")
    public List<ParkingSpace> getAllParkingspaces(){
        return parkingSpaceService.getAllParkingSpaces();
    }


    @GetMapping("/free")
    public List<ParkingSpace> getAvaiableParkingSpaces(){
        return parkingSpaceService.getNotOccupied();
    }


    @GetMapping("/{id}")
    public ParkingSpace getParkingSpacebyId(@PathVariable Long id){
        return parkingSpaceService.getParkingSpaceById(id);
    }

    @PostMapping("/{id}")
    public List<ParkingSpace> createParkingSpace(@PathVariable Long id){

        ParkingLot parkingLot= parkingLotService.getParkingLotById(id);
        return parkingSpaceService.createParkingSpace(parkingLot);
    }
}
