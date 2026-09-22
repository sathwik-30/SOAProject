package com.example.parking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parking.entity.ParkingSlot;
import com.example.parking.service.ParkingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/slots")
    public ResponseEntity<ParkingSlot> addSlot(
            @Valid @RequestBody ParkingSlot parkingSlot) {

        return ResponseEntity.ok(parkingService.addSlot(parkingSlot));
    }

    @GetMapping("/slots")
    public ResponseEntity<List<ParkingSlot>> getAllSlots() {

        return ResponseEntity.ok(
                parkingService.getAllSlots()
        );
    }

    @GetMapping("/slots/{id}")
    public ResponseEntity<ParkingSlot> getSlotById(
            @PathVariable Long id) {

        return parkingService.getSlotById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/slots/{id}/availability")
    public ResponseEntity<ParkingSlot> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        return ResponseEntity.ok(
                parkingService.updateAvailability(id, available)
        );
    }
}