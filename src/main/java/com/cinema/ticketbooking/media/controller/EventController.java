package com.cinema.ticketbooking.media.controller;

import com.cinema.ticketbooking.dto.requestDto.EventRequestDto;
import com.cinema.ticketbooking.dto.responseDto.EventResponseDto;
import com.cinema.ticketbooking.media.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final IEventService eventService;

    @GetMapping(path={"","/"})
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable int id) {
        EventResponseDto event = eventService.getEventById(id);
        return  ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path={"","/"})
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto eventRequestDto) {
        EventResponseDto event = eventService.createEvent(eventRequestDto);
        return ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable int id, @RequestBody EventRequestDto eventRequestDto) {
        EventResponseDto event = eventService.updateEvent(id, eventRequestDto);
        return ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/change-status")
    public ResponseEntity<Void> updateEventStatus(@PathVariable Integer id, @RequestParam String status) {
        eventService.updateEventStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
