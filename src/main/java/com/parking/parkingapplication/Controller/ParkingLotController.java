package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.Model.ParkingLot;
import com.parking.parkingapplication.Model.ParkingSpace;
import com.parking.parkingapplication.Service.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/parkinglot")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("")
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable Long id) {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    @GetMapping("/{id}/free")
    public List<ParkingSpace> getAvailableParkingLotById(@PathVariable Long id) {
        return parkingLotService.getAvailableParkingSpaces(id);
    }

    @GetMapping("/{id}/parkingspaces")
    public  List<ParkingSpace>getParkingSpacesByLotId(@PathVariable Long id){
        return parkingLotService.getAllParkingSpaces(id);
    }

    @PostMapping("")
    public ResponseEntity<ParkingLot> createParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLot.setAvailableSpaces(parkingLot.getCapacity());
        ParkingLot createdParkingLot = parkingLotService.createParkingLot(parkingLot);
        return new ResponseEntity<>(createdParkingLot, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable Long id, @RequestBody ParkingLot parkingLot) {
        parkingLotService.updateParkingLot(parkingLot);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParkingLot> deleteParkingLot(@PathVariable Long id) {
        parkingLotService.deleteParkingLotById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
