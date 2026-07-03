package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IPriceListService;
import com.cinema.ticketbooking.dto.requestDto.PriceListRequestDto;
import com.cinema.ticketbooking.dto.requestDto.SeatPriceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.PriceListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-lists")
@RequiredArgsConstructor
public class PriceListController {
    private final IPriceListService priceListService;

    @GetMapping("")
    public ResponseEntity<List<PriceListResponseDto>> getAllPriceLists() {
        List<PriceListResponseDto> priceLists = priceListService.getAllPriceLists();
        return ResponseEntity.ok(priceLists);
    }

    @GetMapping("/audience-types/{audienceTypeId}")
    public ResponseEntity<List<PriceListResponseDto>> getPriceListByAudienceType(@PathVariable Integer audienceTypeId) {
        List<PriceListResponseDto> priceLists = priceListService.getPriceListByAudienceTypeId(audienceTypeId);
        return ResponseEntity.ok(priceLists);
    };

    @GetMapping("/{id}")
    public ResponseEntity<PriceListResponseDto> getPriceListById(@PathVariable Integer id) {
        PriceListResponseDto priceList = priceListService.getPriceListById(id);
        return ResponseEntity.ok(priceList);
    }



    @PostMapping("/seat-price")
    public ResponseEntity<PriceListResponseDto> getSeatPrice(@RequestBody SeatPriceRequestDto seatPriceRequestDto) {
        PriceListResponseDto priceList = priceListService.getSeatPrice(seatPriceRequestDto.seatId(),
                seatPriceRequestDto.audienceTypeId(),seatPriceRequestDto.date());
        return ResponseEntity.ok(priceList);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<PriceListResponseDto> createPriceList(@RequestBody PriceListRequestDto priceListRequestDto) {
        PriceListResponseDto createdPriceList = priceListService.createPriceList(priceListRequestDto);
        return ResponseEntity.ok(createdPriceList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<PriceListResponseDto> updatePriceList(@PathVariable Integer id, @RequestBody PriceListRequestDto priceListRequestDto) {
        PriceListResponseDto updatedPriceList = priceListService.updatePriceList(id, priceListRequestDto);
        return ResponseEntity.ok(updatedPriceList);
    }
}
