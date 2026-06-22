package com.cinema.ticketbooking.hall.controller;

import com.cinema.ticketbooking.dto.SeatTypeDto;
import com.cinema.ticketbooking.hall.service.ISeatTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat-types")
@RequiredArgsConstructor
public class SeatTypeController {

    private final ISeatTypeService seatTypeService;

    @GetMapping({"","/"})
    public ResponseEntity<List<SeatTypeDto>> getAllSeatTypes(){
        List<SeatTypeDto> seatTypeDtos = seatTypeService.getAllSeatTypes();

        return ResponseEntity.ok(seatTypeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatTypeDto> getSeatTypeById(@PathVariable Integer id){
        SeatTypeDto seatTypeDto = seatTypeService.getSeatTypeById(id);
        return ResponseEntity.ok(seatTypeDto);
    }
//
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<SeatTypeDto>> getSeatTypesByStatus(@PathVariable String status){
//        List<SeatTypeDto> seatTypeDtos = seatTypeService.getAllSeatTypes().stream()
//                .filter(seatTypeDto -> seatTypeDto.status().equalsIgnoreCase(status))
//                .toList();
//        return ResponseEntity.ok(seatTypeDtos);
//    }



    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<SeatTypeDto> updateSeatType(@PathVariable Integer id, @RequestBody SeatTypeDto seatTypeDto){
        SeatTypeDto seatTypeUpdated =  seatTypeService.updateSeatType(id, seatTypeDto);
        return  ResponseEntity.ok(seatTypeUpdated);
    }

    @PostMapping
    public ResponseEntity<SeatTypeDto> createSeatType(@RequestBody SeatTypeDto seatTypeDto){
        SeatTypeDto createdSeatType = seatTypeService.createSeatType(seatTypeDto);
        return ResponseEntity.ok(createdSeatType);
    }


}
