package com.parking.parkingapplication.Service;

import com.parking.parkingapplication.Model.ParkingSpace;
import com.parking.parkingapplication.Model.ParkingSpaceStatus;
import com.parking.parkingapplication.Model.ParkingTransaction;
import com.parking.parkingapplication.Model.Vehicle;
import com.parking.parkingapplication.Repository.ParkingSpaceRepository;
import com.parking.parkingapplication.Repository.ParkingTransactionRepository;
import com.parking.parkingapplication.Repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ParkingTransactionService {


    private final ParkingTransactionRepository parkingTransactionRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingTransactionService(ParkingTransactionRepository parkingTransactionRepository, VehicleRepository vehicleRepository, ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingTransactionRepository = parkingTransactionRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }


    public List<ParkingTransaction> getAllParkingTransactions(){
        return parkingTransactionRepository.findAll();
    }

    public ParkingTransaction getParkingTransactionById(Long id){
        return parkingTransactionRepository.findById(id).orElse(null);
    }
    public ParkingTransaction finishTransaction(Long transactionId){
        try{
            ParkingTransaction parkingTransaction = parkingTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalArgumentException("ParkingTransaction not found with id: " + transactionId));
            if (parkingTransaction.getDuration()!=null||
                    parkingTransaction.getParkingSpace().getParkingLot().getCapacity()==parkingTransaction.getParkingSpace().getParkingLot().getAvailableSpaces()){
                return null;
            }
            parkingTransaction.setEndTime(LocalDateTime.now());
            Duration duration=Duration.between
                    (parkingTransaction.getStartTime(),parkingTransaction.getEndTime());
            if (duration.toHours()>0){
                parkingTransaction.setDuration(duration);
                parkingTransaction.setPrice(getPrice(parkingTransaction));
            }
            parkingTransaction.setDuration(duration);
            parkingTransaction.getParkingSpace()
                    .setParkingSpaceStatus(ParkingSpaceStatus.AVAILABLE);
            parkingTransaction.getParkingSpace()
                    .setOccupied(!parkingTransaction.getParkingSpace().getOccupied());
            parkingTransaction.getParkingSpace().getParkingLot()
                    .setAvailableSpaces(parkingTransaction.getParkingSpace().getParkingLot().getAvailableSpaces()+1);
            return parkingTransactionRepository.save(parkingTransaction);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    private Double getPrice(ParkingTransaction parkingTransaction) {
        return 2.0*parkingTransaction.getDuration().toHours();
    }

    public void deleteParkingTransactionById(Long id){
        parkingTransactionRepository.deleteById(id);
    }

    public ParkingTransaction createTransaction(ParkingTransaction parkingTransaction, Long vehicleId, Long parkingSpaceId) {
        Vehicle vehicle= vehicleRepository.findById(vehicleId).orElseThrow();
        ParkingSpace parkingSpace= parkingSpaceRepository.findById(parkingSpaceId).orElseThrow();
        try{
            if (parkingSpace.getParkingLot().getAvailableSpaces()==0){
                throw new NullPointerException();
            }
            parkingTransaction.setStartTime(LocalDateTime.now());
            vehicle.setParkingTransactions(new ArrayList<>());
            parkingTransaction.setVehicle(vehicle);
            parkingSpace.setParkingSpaceStatus(ParkingSpaceStatus.OCCUPIED);
            parkingSpace.setOccupied(!parkingSpace.getOccupied());
            parkingTransaction.setParkingSpace(parkingSpace);
            parkingSpace.getParkingLot().setAvailableSpaces(parkingSpace.getParkingLot().getAvailableSpaces()-1);
            return parkingTransactionRepository.save(parkingTransaction);
        }catch (NullPointerException e){
            e.getMessage();

        }
        return null;
    }
}
