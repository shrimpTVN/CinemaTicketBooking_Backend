package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IShowtimeService;
import com.cinema.ticketbooking.dto.requestDto.ShowtimeRequestDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final IShowtimeService showtimeService;

    @GetMapping({"","/"})
    public ResponseEntity<List<ShowtimeResponseDto>> getAllShowtimes() {
        List<ShowtimeResponseDto> showtimes = showtimeService.getAllShowtimes();
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/showing")
    public ResponseEntity<List<ShowtimeResponseDto>> filterShowtimes(@RequestParam(required = false) Integer movieId,
                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<ShowtimeResponseDto> showtimes;
        if (movieId == null){
            showtimes = showtimeService.getShowtimesByDate(date);
        } else if (date == null){
            showtimes = showtimeService.getShowtimesByMovieId(movieId);
        } else if (movieId != null && date != null) {
            showtimes = showtimeService.getShowtimesByDate(date).stream().filter(s -> s.movieId().equals(movieId)).toList();
        } else{
           showtimes = showtimeService.getAllShowtimes();
        }

        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeResponseDto> getShowTimeById(@PathVariable Integer id){
        ShowtimeResponseDto showtime = showtimeService.getShowtimeById(id);
        return ResponseEntity.ok(showtime);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/",""})
    public ResponseEntity<ShowtimeResponseDto> createShowtime(@RequestBody ShowtimeRequestDto showtimeRequestDto){
        System.out.println(showtimeRequestDto);
        ShowtimeResponseDto newShowtime = showtimeService.createShowtime(showtimeRequestDto);
        return ResponseEntity.ok(newShowtime);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<ShowtimeResponseDto> updateShowtime(@PathVariable Integer id, @RequestBody ShowtimeRequestDto showtimeRequestDto){
        ShowtimeResponseDto updatedShowtime = showtimeService.updateShowtime(id, showtimeRequestDto);
        return ResponseEntity.ok(updatedShowtime);
    }

}
