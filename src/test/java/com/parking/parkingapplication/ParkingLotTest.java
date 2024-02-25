package com.parking.parkingapplication;

import com.parking.parkingapplication.Model.ParkingLot;
import com.parking.parkingapplication.Repository.ParkingLotRepository;
import com.parking.parkingapplication.Service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Mock
    private ParkingLotRepository parkingLotRepository;

    @InjectMocks
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllParkingLotsReturnsEmptyList() {
        // Arrange
        when(parkingLotRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<ParkingLot> result = parkingLotService.getAllParkingLots();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllParkingLotsReturnsNonEmptyList() {
        // Arrange
        ParkingLot parkingLot1 = new ParkingLot(/* your constructor parameters here */);
        ParkingLot parkingLot2 = new ParkingLot(/* your constructor parameters here */);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);

        when(parkingLotRepository.findAll()).thenReturn(parkingLots);

        // Act
        List<ParkingLot> result = parkingLotService.getAllParkingLots();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(parkingLot1));
        assertTrue(result.contains(parkingLot2));
    }
    @Test
    void testDeleteParkingLotById() {
        // Arrange
        Long parkingLotId = 1L;

        // Act
        parkingLotService.deleteParkingLotById(parkingLotId);

        // Assert
        verify(parkingLotRepository, times(1)).deleteById(eq(parkingLotId));
    }

    // Add more tests as needed

}