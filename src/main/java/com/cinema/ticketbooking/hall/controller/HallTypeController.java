package com.cinema.ticketbooking.hall.controller;

import com.cinema.ticketbooking.dto.HallTypeDto;
import com.cinema.ticketbooking.hall.service.IHallTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall-types")
@RequiredArgsConstructor
public class HallTypeController {
    private final IHallTypeService hallTypeService;

    @GetMapping({"/",""})
    public ResponseEntity<List<HallTypeDto>> findAllHallType(@RequestParam(required = false) String name){

        if (name != null) {
            var hallType = hallTypeService.getHallTypeByName(name);
            return ResponseEntity.ok(List.of(hallType));
        }

        List<HallTypeDto> hallTypes = hallTypeService.getAllHallTypes();
        return ResponseEntity.ok(hallTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallTypeDto> findHallTypeById(@PathVariable Integer id) {
        HallTypeDto hallType = hallTypeService.getHallTypeById(id);
        return ResponseEntity.ok(hallType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<HallTypeDto> createHallType(@RequestBody HallTypeDto hallTypeDto) {
        HallTypeDto createdHallType = hallTypeService.createHallType(hallTypeDto);
        return ResponseEntity.ok(createdHallType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<HallTypeDto> updateHallType(@PathVariable Integer id, @RequestBody HallTypeDto hallTypeDto) {
        HallTypeDto updatedHallType = hallTypeService.updateHallType(id, hallTypeDto);
        return ResponseEntity.ok(updatedHallType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<HallTypeDto> updateHallTypeWithPut(@PathVariable Integer id, @RequestBody HallTypeDto hallTypeDto) {
        var hallType = hallTypeService.getHallTypeById(id);
        if (hallType != null) {
            HallTypeDto updatedHallType = hallTypeService.updateHallType(id, hallTypeDto);
            return ResponseEntity.ok(updatedHallType);
        } else {
            HallTypeDto newHallType = hallTypeService.createHallType(hallTypeDto);
            return ResponseEntity.ok(newHallType);
        }
    }

}
