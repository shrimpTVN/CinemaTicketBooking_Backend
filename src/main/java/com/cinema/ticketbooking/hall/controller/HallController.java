package com.cinema.ticketbooking.hall.controller;

import com.cinema.ticketbooking.dto.requestDto.HallRequestDto;
import com.cinema.ticketbooking.dto.responseDto.HallResponseDto;
import com.cinema.ticketbooking.dto.SeatDto;
import com.cinema.ticketbooking.hall.service.IHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final IHallService hallService;

    @GetMapping({"/",""})
    public ResponseEntity<List<HallResponseDto>> getAllHalls() {
        List<HallResponseDto> hallDtos = hallService.getAllHalls();
        return ResponseEntity.ok(hallDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallResponseDto> getHallById(@PathVariable Integer id) {
        HallResponseDto hallDto = hallService.getHallById(id);
        return  ResponseEntity.ok(hallDto);
    }

    @GetMapping("/{id}/seat-map")
    public ResponseEntity<List<SeatDto>> getHallSeatMap(@PathVariable Integer id) {
        return ResponseEntity.ok(hallService.getHallSeatMap(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/seat-map")
    public ResponseEntity<List<SeatDto>> generateHallSeatMap(@PathVariable("id") Integer id, @RequestBody List<SeatDto> seatDtos){
        return ResponseEntity.ok(hallService.generateHallSeatMap(id, seatDtos));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/seat-map")
    public ResponseEntity<List<SeatDto>> updateHallSeatMap(@PathVariable("id") Integer id,  @RequestBody List<SeatDto> seatDtos){
        return ResponseEntity.ok(hallService.updateHallSeatMap(id, seatDtos));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<HallResponseDto> updateHall(@PathVariable Integer id, @RequestBody HallRequestDto hallDto) {
        HallResponseDto updatedHallDto = hallService.updateHall(id, hallDto);
        return ResponseEntity.ok(updatedHallDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<HallResponseDto> createHall(@RequestBody HallRequestDto hallDto) {
        HallResponseDto newHallDto = hallService.createHall(hallDto);
        return ResponseEntity.ok(newHallDto);
    }


}
