package com.cinema.ticketbooking.hall.controller;

import com.cinema.ticketbooking.dto.HallDto;
import com.cinema.ticketbooking.entity.Hall;
import com.cinema.ticketbooking.hall.service.IHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final IHallService hallService;

    @GetMapping({"/",""})
    public ResponseEntity<List<HallDto>> getAllHalls() {
        List<HallDto> hallDtos = hallService.getAllHalls();
        return ResponseEntity.ok(hallDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDto> getHallById(@PathVariable Integer id) {
        HallDto hallDto = hallService.getHallById(id);
        return  ResponseEntity.ok(hallDto);
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<HallDto> updateHall(@PathVariable Integer id, @RequestBody HallDto hallDto) {
        HallDto updatedHallDto = hallService.updateHall(id, hallDto);
        return ResponseEntity.ok(updatedHallDto);
    }

    @PostMapping("")
    public ResponseEntity<HallDto> createHall(@RequestBody HallDto hallDto) {
        HallDto newHallDto = hallService.createHall(hallDto);
        return ResponseEntity.ok(newHallDto);
    }
}
