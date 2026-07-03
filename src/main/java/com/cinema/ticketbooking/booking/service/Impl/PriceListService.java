package com.cinema.ticketbooking.booking.service.Impl;

import com.cinema.ticketbooking.AudienceTypeRepository;
import com.cinema.ticketbooking.booking.service.IPriceListService;
import com.cinema.ticketbooking.core.exception.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.requestDto.PriceListRequestDto;
import com.cinema.ticketbooking.entity.*;
import com.cinema.ticketbooking.dto.responseDto.PriceListResponseDto;
import com.cinema.ticketbooking.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PriceListService implements IPriceListService {
    private final PriceListRepository priceListRepository;
    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final HallTypeRepository hallTypeRepository;
    private final AudienceTypeRepository audienceTypeRepository;


    @Override
    public List<PriceListResponseDto> getAllPriceLists() {
        return priceListRepository.findAll().stream()
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public PriceListResponseDto getPriceListById(int id) {
        PriceList priceList = priceListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price list not found with id: " + id));
        return transformToDto(priceList);
    }

    @Override
    public List<PriceListResponseDto> getPriceListByAudienceTypeId(Integer audienceTypeId) {
        if (audienceTypeId == null) {
            throw new IllegalArgumentException("audienceTypeId is required");
        }

        List<PriceList> priceLists = priceListRepository.findByAudienceTypeId(audienceTypeId);
        if (priceLists.isEmpty()) {
            throw new ResourceNotFoundException("Price list not found for audience type id: " + audienceTypeId);
        }

        return priceLists.stream()
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public PriceListResponseDto getSeatPrice(Integer seatId, Integer audienceTypeId, LocalDate date) {
        if (seatId == null || audienceTypeId == null || date == null) {
            throw new IllegalArgumentException("seatId, audienceTypeId, and date are required");
        }
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));
        Hall hall = seat.getHall();
        HallType hallType = hall.getHallType();
        SeatType seatType = seat.getSeatType();

        List<PriceList> priceLists = priceListRepository.findByReferences(hallType.getId(), seatType.getId(), audienceTypeId);
        if (priceLists.isEmpty()) {
            throw new ResourceNotFoundException("Price list not found for the given references");
        }

        PriceList priceList = null;

        String targetDay = getDayCode(date);
        for(PriceList priceItem : priceLists) {
            if (priceItem.getDays().contains(targetDay)) {
                priceList = priceItem;
                break;
            }
        }

        if (priceList == null) {
            throw new ResourceNotFoundException("Price list not found for the given references and date");
        }

        return transformToDto(priceList);
    }

    private  String getDayCode(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        // Substring(0, 3) safely extracts the first 3 letters:
        // MONDAY -> MON
        // TUESDAY -> TUE
        // WEDNESDAY -> WED
        // THURSDAY -> THU
        // FRIDAY -> FRI
        // SATURDAY -> SAT
        // SUNDAY -> SUN
        return day.name().substring(0, 3);
    }

    @Override
    public PriceListResponseDto createPriceList(PriceListRequestDto priceListRequestDto) {
        if (priceListRequestDto == null) {
            throw new IllegalArgumentException("Price list payload is required");
        }

        PriceList priceList = new PriceList();
        HallType hallType = hallTypeRepository.findById(priceListRequestDto.hallTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + priceListRequestDto.hallTypeId()));
        SeatType seatType = seatTypeRepository.findById(priceListRequestDto.seatTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat type not found with id: " + priceListRequestDto.seatTypeId()));
        AudienceType audienceType = audienceTypeRepository.findById(priceListRequestDto.audienceTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Audience type not found with id: " + priceListRequestDto.audienceTypeId()));

        priceList.setHallType(hallType);
        priceList.setSeatType(seatType);
        priceList.setAudienceType(audienceType);
        priceList.setName(priceListRequestDto.name());
        priceList.setPrice(priceListRequestDto.price());
        priceList.setDays(priceListRequestDto.days());
        priceList.setStatus( "ON");

        PriceList savedPriceList = priceListRepository.save(priceList);
        return transformToDto(savedPriceList);
    }

    @Override
    public PriceListResponseDto updatePriceList(Integer id, PriceListRequestDto priceListRequestDto) {
        PriceList priceList = priceListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price list not found with id: " + id));

        if (priceListRequestDto == null) {
            throw new IllegalArgumentException("Price list payload is required");
        }

        priceList.setName(priceListRequestDto.name());
        priceList.setPrice(priceListRequestDto.price());
        priceList.setDays(priceListRequestDto.days());

        PriceList updatedPriceList = priceListRepository.save(priceList);
        return transformToDto(updatedPriceList);
    }

    @Override
    public void updatePriceListStatus(Integer id, String status) {
        PriceList priceList = priceListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price list not found with id: " + id));
        priceList.setStatus(status);
        priceListRepository.save(priceList);
    }

    private PriceListResponseDto transformToDto(PriceList priceList) {
        return new PriceListResponseDto(
                priceList.getId(),
                priceList.getHallType().getName(),
                priceList.getSeatType().getName(),
                priceList.getAudienceType().getName(),
                priceList.getName(),
                priceList.getPrice(),
                priceList.getDays(),
                priceList.getStatus()
        );
    }
}
