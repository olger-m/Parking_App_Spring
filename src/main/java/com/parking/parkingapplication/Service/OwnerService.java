package com.parking.parkingapplication.Service;

import com.parking.parkingapplication.Model.Owner;
import com.parking.parkingapplication.Model.ParkingTransaction;
import com.parking.parkingapplication.Repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwners(){return  ownerRepository.findAll();}

    public Owner getOwnerbyId(Long id){
        return ownerRepository.findById(id).orElse(null);
    }
    public Owner createOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public List<ParkingTransaction> getOwnerTransactions(Long id) {
        return ownerRepository.findById(id).orElse(null).getParkingTransactions();
    }
}
