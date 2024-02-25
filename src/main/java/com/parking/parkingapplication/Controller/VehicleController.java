package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.Model.Vehicle;
import com.parking.parkingapplication.Service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping("")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle= vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
