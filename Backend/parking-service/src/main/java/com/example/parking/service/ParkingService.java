package com.example.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.parking.entity.ParkingSlot;
import com.example.parking.repository.ParkingSlotRepository;

@Service
public class ParkingService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public ParkingSlot addSlot(ParkingSlot parkingSlot) {
        return parkingSlotRepository.save(parkingSlot);
    }

    public List<ParkingSlot> getAllSlots() {
        return parkingSlotRepository.findAll();
    }

    public Optional<ParkingSlot> getSlotById(Long id) {
        return parkingSlotRepository.findById(id);
    }

    public ParkingSlot updateAvailability(Long id, boolean available) {

        ParkingSlot slot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Parking slot not found"));

        slot.setAvailable(available);

        return parkingSlotRepository.save(slot);
    }
}