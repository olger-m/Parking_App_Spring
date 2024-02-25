package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.Model.Owner;
import com.parking.parkingapplication.Model.ParkingTransaction;
import com.parking.parkingapplication.Service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("")
    public Owner createOwner(Owner owner){
        return ownerService.createOwner(owner);
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id){
        return ownerService.getOwnerbyId(id);
    }
    @GetMapping("/{id}/transactions")
    public List<ParkingTransaction> getOwnerTransactions(@PathVariable Long id){
        return ownerService.getOwnerTransactions(id);
    }


}
