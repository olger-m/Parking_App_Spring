package com.parking.parkingapplication.Service;

import com.parking.parkingapplication.Model.Vehicle;
import com.parking.parkingapplication.Repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElse(null);
    }
    public void updateVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicleById(Long id){
        vehicleRepository.deleteById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
