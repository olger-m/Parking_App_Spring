package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.Model.ParkingTransaction;
import com.parking.parkingapplication.Service.ParkingSpaceService;
import com.parking.parkingapplication.Service.ParkingTransactionService;
import com.parking.parkingapplication.Service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transaction")
public class TransactionController {

 private final ParkingTransactionService transactionService;
 private final VehicleService vehicleService;
 private final ParkingSpaceService parkingSpaceService;

    public TransactionController(ParkingTransactionService transactionService, VehicleService vehicleService, ParkingSpaceService parkingSpaceService) {
        this.transactionService = transactionService;
        this.vehicleService = vehicleService;
        this.parkingSpaceService = parkingSpaceService;
    }

    @GetMapping("")
    public List<ParkingTransaction> getAllTransactions() {
        return transactionService.getAllParkingTransactions();
    }

    @PostMapping("")
    public ParkingTransaction createTransaction(@RequestParam Long vehicleId,Long parkingSpaceId,@RequestBody ParkingTransaction parkingTransaction){
        return transactionService.createTransaction(parkingTransaction,vehicleId,parkingSpaceId);
    }
    @PutMapping("/finish")
    public ParkingTransaction finishTransaction(@RequestParam Long transactionId){
        return transactionService.finishTransaction(transactionId);

    }


}
